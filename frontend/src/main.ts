import {createApp} from 'vue'
import App from '@/App.vue'
import router from '@/router'
import './index.css'
import apiService from './api.service'

createApp(App)
    .use(router)
    .use(apiService)
    .mount('#app')
