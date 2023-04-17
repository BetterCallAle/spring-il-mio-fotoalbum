<template>
    <AppHeader />

    <div class="container py-4">
        <h1 class="text-center">Contattami</h1>

        <div class="row justify-content-center">
            <form class="col-7" @submit.prevent="sendMessage(), checkForm()">
                <div class="mb-3 d-flex justify-content-between">
                    <div class="user-info">
                        <label for="name" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="name" aria-describedby="name" name="name" v-model.trim="name" required>
                        <small class="text-danger" v-if="errorName">Questo campo è obbligatorio</small>
                    </div>
                    
                    <div class="user-info">
                        <label for="surname" class="form-label">Cognome</label>
                        <input type="text" class="form-control" id="surname" aria-describedby="surname" name="surname" v-model.trim="surname">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" v-model.trim="email" required>
                    <small class="text-danger" v-if="errorEmail">Inserisci una email valida</small>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Messaggio</label>
                    <textarea name="message" id="message" rows="10" class="form-control" v-model.trim="message" required></textarea>
                    <small class="text-danger" v-if="errorMessage">Questo campo è obbligatorio</small>
                </div>
    
                <button type="submit" class="btn" :class="checkFormInputs, changeSubmitBackgrond">Invia</button>
                <button type="submit" class="btn btn-primary ms-2">Resetta i campi</button>
            </form>
        </div>

    </div>
</template>


<script>
import AppHeader from '../components/AppHeader.vue';
import axios from 'axios';
export default {
    name: "ContactPage",
    components: {AppHeader},
    data(){
        return{
            apiUrl : 'http://localhost:8080/api/messages',
            name: "",
            surname: "",
            email: "",
            message: "",
            errorName: false,
            errorEmail: false,
            errorMessage: false
        }
    },
    methods:{
        sendMessage(){
            const params = {
                name: this.name,
                surname: this.surname,
                email: this.email,
                message: this.message,
            }

            axios.post(this.apiUrl, params).then(response =>{
                console.log(response);
                this.name = "";
                this.surname = "";
                this.email = "";
                this.message = "";
            })
        }
    },
    computed:{
        checkFormInputs(){
            return (this.name === "" || this.email === ""|| !this.email.includes("@") || !this.email.includes(".") || this.message === "") ? 'not-clickable' : 'clickable'
        },

        changeSubmitBackgrond(){
            return (this.name === "" || this.email === "" || !this.email.includes("@") || !this.email.includes(".") ||this.message === "") ? 'btn-secondary' : 'btn-danger'
        }
    }
}
</script>

<style lang="scss" scoped>
    .user-info{
        width: 48%;
    }

    .not-clickable{
        pointer-events: none;
    }
</style>