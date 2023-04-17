<script>
import axios from "axios";
import AppHeader from "../components/AppHeader.vue";
import PhotoCard from "../components/PhotoCard.vue";

export default{
    name: 'HomePage',
    components: {AppHeader, PhotoCard},
    data(){
        return{
            apiUrl : 'http://localhost:8080/api/photos',
            imgUrl: 'http://localhost:8080/api/photos',
            photos: [],
            index: 0,
            search: false,
            searchInput: "",
            load: false,
        }
    },
    created(){
        this.getAllPhotos();
        setInterval(this.goToNextPhoto, 5000);
    },
    methods:{
        getAllPhotos(){
            axios.get(this.apiUrl).then(resp=>{
                this.photos = resp.data;
                for (let index = 0; index < this.photos.length; index++) {
                    const element = this.photos[index];
                    if(element.imageFile){
                        console.log(element);
                        const id = element.imageFile.id;
                        element.imageFile = `http://localhost:8080/file/${id}`
                    }
                }
            })
        },

        goToNextPhoto(){
            if(this.photos.length-1 <= this.index){
                this.index = 0;
            } else {
                this.index ++
            }
        },

        goToPrevPhoto(){
            if(this.index === 0){
                this.index = this.photos.length-1;
            } else {
                this.index--;
            }
        },

        searchPhotoByInput(){
            const params = {
                title: this.searchInput
            };

            axios.get(this.apiUrl, {params}).then(resp=>{
                console.log(resp.data);
                this.photos = resp.data;
                this.searchInput = "";
                this.search = true;
            })
        }
    }
}
</script>

<template>

    <AppHeader />

    <section v-if="!search" id="carousel" class="carousel">
        <img v-if="!photos[index].imageFile" :src="photos[index].imgUrl" :alt="photos[index].title">
        <img v-else :src="photos[index].imageFile" :alt="photos[index].title">

        <div class="image-info">
            <div class="image-categories">
                <ul class="categories-list">
                    <li class="categories-list-element d-inline-block me-2" v-for="category in photos[index].categories">
                        <span>#{{ category.name }}</span>
                    </li>
                </ul>
            </div>
            <h3 class="image-title text-categories">{{ photos[index].title }}</h3>
            <p class="image-descritpion text-categories">{{ photos[index].description }}</p>
        </div>

        <div class="button-wrapper d-flex justify-content-between">
            <button @click="goToPrevPhoto()" class="carousel-btn">
                <i class="fa-solid fa-chevron-left"></i>
            </button>
            <button @click="goToNextPhoto()" class="carousel-btn">
                <i class="fa-solid fa-chevron-right"></i>
            </button>
        </div>
    </section>

    <section id="carousel" class="main">
        <div class="container py-4">
            <form @submit.prevent="searchPhotoByInput()">
                <div class="mb-3">
                    <input type="text" class="search-input" placeholder="Cerca una foto per titolo" v-model="searchInput">
                    <button class="my-btn">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>
            </form>

            <div class="row py-2">
                <template  v-for="photo in photos">
                    <PhotoCard :photo="photo" />
                </template>
            </div>
        </div>
    </section>


</template>

<style lang="scss" scoped>
    .carousel{
        height: 50vh;
        position: relative;
        img{
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .image-info{
            position: absolute;
            top: 10%;
            left: 10%;
            opacity: 0%;
            transition: all 300ms linear;
            .text-categories{
                color: white;
                text-shadow: 2px 2px #d84113;
            }
            .categories-list{
                list-style-type: none;
                padding: 0;
                .categories-list-element{
                    background-color: rgba(255, 255, 255, 0.425);
                    font-size: .9rem;
                    padding: .4rem;
                    color: #000;
                    font-weight: 900;
                    mix-blend-mode: screen;
                    border-radius: 20px;
                    box-shadow: 1px 2px #d84113;
                }
            }
        }

        .button-wrapper{
            width: 100%;
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            .carousel-btn{
                opacity: 0%;
                background-color: transparent;
                padding: .3rem .8rem;
                border: 1px solid white;
                border-radius: 50%;
                margin-left: .5rem;
                margin-right: .5rem;
                transition: all 200ms linear;
                box-shadow: 1px 2px #d84113;
                i{
                    color: white;
                    text-shadow: 2px 2px #d84113;
                }
            }
        }

        &:hover{
            .carousel-btn, .image-info{
                opacity: 100%;
            }
        }
    }

    .main{
        h1{
            color: #d84113;
            font-size: 1.8rem;
        }

        form{
            text-align: center;
            .search-input{
                width: 80%;
                border: 1px solid #d84113;
                padding: .3rem .7rem;
                border-top-left-radius: 20px;
                border-bottom-left-radius: 20px;

                &:focus{
                    outline: none;
                }
            }

            .my-btn{
                background-color: #d84113;
                padding: .3rem 1rem;
                border: 1px solid #d84113;
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;

                i{
                    color: white;
                }
            }
        }

    }
</style>