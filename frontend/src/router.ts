import {
    createRouter,
    createWebHashHistory
} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {path: '/', component: () => import('./views/Home.vue')},
        {path: '/hello', component: () => import('./views/Hello.vue')},
    ]
})

export default router