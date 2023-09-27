import axios from 'axios'
import {App, provide} from 'vue'

const BASE_URL = 'http://localhost:8080'

const plugin = {
    install: (app: App) => {
        app.provide('get', (path: string) => axios.get(BASE_URL + path))
        app.provide('post', (path: string, body: any) => axios.post(BASE_URL + path, body))
    }
}

export default plugin
