import {createApp} from 'vue'
import App from '@/App.vue'
import router from '@/router'
import './index.css'
import apiService from './services/api.service'

createApp(App)
    .use(router)
    .use(apiService)
    .mount('#app')
