import {
    createRouter,
    createWebHashHistory
} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {path: '/home', component: () => import('./views/HomeView.vue')},
        {path: '/accountMatcher', component: () => import('./views/AccountMatcherView.vue')},
        {path: '/transactions', component: () => import('./views/ComparedPeriods.vue')},
    ]
})

export default router