import { createRouter, createWebHashHistory, createWebHistory } from "vue-router";
import HomePage from "./pages/HomePage.vue";
import ContactPage from "./pages/ContactPage.vue";
import ShowPage from "./pages/ShowPage.vue";
import AboutMe from "./pages/AboutMe.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "home",
            component: HomePage
        },
        {
            path: "/contact-me",
            name: "contact",
            component: ContactPage
        },
        {
            path: "/photos/:id",
            name: "show",
            component: ShowPage
        },
        {
            path: "/about",
            name: "about",
            component: AboutMe
        }
    ]
});

export { router };