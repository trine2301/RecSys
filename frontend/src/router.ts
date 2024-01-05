import {
    createRouter,
    createWebHashHistory
} from 'vue-router'

/**
 * Create a router instance with a hash-based history mode.
 * Define routes, each associating a path with a dynamically imported component.
 */
const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {path: '/home', component: () => import('./views/HomeView.vue')},
        {path: '/accountMatcher', component: () => import('./views/TransactionMatcherView.vue')},
        {path: '/transactions', component: () => import('./views/ComparedPeriods.vue')},
    ]
})

export default router