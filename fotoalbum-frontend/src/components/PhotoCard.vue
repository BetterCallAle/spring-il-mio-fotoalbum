<template>
    <div class="col-4">
        <div class="card" style="width: 18rem;">
            <!-- Img -->
            <div class="card-img">
                <img :src="photo.imgUrl" class="card-img-top" :alt="photo.title">
            </div>
            <!-- /Img -->
            <div class="card-body">
                <!-- Categories -->
                <div class="categories">
                    <ul class="list">
                        <li class="categories-list-element d-inline-block me-2" v-for="category in photo.categories">
                            <span>#{{ category.name }}</span>
                        </li>
                    </ul>
                </div>
                <!-- /Categories -->
                <h5 class="card-title">{{ photo.title }}</h5>
                <p class="card-text">{{ photo.description }}</p>
                <router-link :to="{name: 'show', params: {id: photo.id}}" class="btn btn-danger">Visualizza</router-link>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: "PhotoCard",
    props: {
        photo : Object
    }
}
</script>


<style lang="scss" scoped>
.card{
    height: 100% !important;
    width: 100% !important;
    transform: scale(1);
    transition: all 124ms linear;
    .card-img{
        height: 50%;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
        overflow-y: hidden;

        img{
            height: 100%;
            object-fit: cover;
        }
    }

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

    &:hover{
        transform: scale(1.1);
    }
}
    
</style>