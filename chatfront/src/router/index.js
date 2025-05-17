import { createRouter } from "vue-router"
import { createWebHistory } from 'vue-router'
import MemberCreate from '@/views/MemberCreate.vue'

const routes = [
    {
        path: '/member/create', 
        name: 'MemberCreate',
        component: MemberCreate
    }

]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;