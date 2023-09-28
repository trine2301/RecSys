/// <reference types="vitest" />
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath, URL} from 'url'

export default defineConfig({
    plugins: [vue()],
    server: {
        hmr: {
            port: 3000
        },
        port: 5172,
        strictPort: true,
        host: true
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL("./src", import.meta.url))
        }
    },
    test: {
        globals: true,
        environment: 'jsdom'
    },

})
