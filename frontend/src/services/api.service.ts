import axios from 'axios'
import {App, provide} from 'vue'


/**
 * Base URL for the api
 */
const BASE_URL = 'http://localhost:8080'


/**
 * Vue plugin for providing methods for making GET and POST requests.
 * The plugin provides two methods: 'get' and 'post'.
 * These methods accept a path and optionally a request body, and return a promise that resolves to the response of the HTTP request.
 */
const plugin = {
    install: (app: App) => {
        app.provide('get', (path: string) => axios.get(BASE_URL + path))
        app.provide('post', (path: string, body: any) => axios.post(BASE_URL + path, body))
    }
}

export default plugin
