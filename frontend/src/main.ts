
/**
 * Main entry point for the Vue.js application.
 * Imports necessary modules, including the main Vue application, the Vue router, global styles, and an API service.
 * Creates the Vue application, registers the router and the API service as plugins, and mounts the application to a DOM element with the id 'app'.
 */
import {createApp} from 'vue'
import App from '@/App.vue'
import router from '@/router'
import './index.css'
import apiService from './services/api.service'

createApp(App)
    .use(router)
    .use(apiService)
    .mount('#app')
