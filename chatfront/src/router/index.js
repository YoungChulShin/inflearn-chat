import { createRouter } from "vue-router"
import { createWebHistory } from 'vue-router'
import MemberCreate from '@/views/MemberCreate.vue'
import LoginPage from '@/views/LoginPage.vue'

const routes = [
    {
        path: '/member/create', 
        name: 'MemberCreate',
        component: MemberCreate
    },
    {
        path: '/login', 
        name: 'LoginPage',
        component: LoginPage
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;