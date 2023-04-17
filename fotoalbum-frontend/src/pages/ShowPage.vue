<template lang="">
    <AppHeader />
    <div class="container py-4">
        <div class="top text-center">
            <!-- Categories -->
            <div class="categories">
                <ul class="list">
                    <li class="categories-list-element d-inline-block me-2" v-for="category in photo.categories">
                        <span>#{{ category.name }}</span>
                    </li>
                </ul>
            </div>
            <h1 class="text-danger">{{ photo.title }}</h1>
            <img v-if="!photo.imageFile" :src="photo.imgUrl" :alt="photo.title">
            <img v-else :src="photo.imageFile" :alt="photo.title">
        </div>
        <p>{{photo.description}}</p>
    </div>
</template>


<script>
import axios from 'axios';
import AppHeader from '../components/AppHeader.vue';
export default {
    name:"ShowPage",
    components: {AppHeader},
    data(){
        return{
            apiUrl : 'http://localhost:8080/api/photos',
            photo: {},
        }
    },
    created(){
        this.getPhotoById();
    },
    methods:{
        getPhotoById(){
            const id = this.$route.params.id;
            axios.get(this.apiUrl + '/' + id).then(resp=>{
                this.photo = resp.data;
                if(this.photo.imageFile){
                    const id = this.photo.imageFile.id;
                    this.photo.imageFile = `http://localhost:8080/file/${id}`
                }
            })
        }
    }
}
</script>


<style lang="scss" scoped>
.categories{
    .list{
        list-style-type: none;
        padding: 0;
        .categories-list-element{
            background-color: #e4e4e44b;
            font-size: .9rem;
            padding: .4rem;
            color: #d84113;
            font-weight: 900;
            border-radius: 20px;
            box-shadow: 1px 2px #d84113;
        }
    }
}
    
</style>