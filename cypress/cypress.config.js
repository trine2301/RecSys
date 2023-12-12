const {defineConfig} = require('cypress')


module.exports = defineConfig({
    e2e: {
        baseUrl: 'http://localhost:5172/',
        viewportHeight: 1080,
        viewportWidth: 1920,
        defaultCommandTimeout: 10000,


        specPattern: 'e2e/**/*.cy.ts',
        videosFolder: 'videos',
        videoCompression: false,
        screenshotsFolder: 'screenshots',
        experimentalInteractiveRunEvents: true,
        supportFile: false
    },

    component: {
        viewportWidth: 1920,
        viewportHeight: 1080,
        devServer: {
            framework: 'vue',
            bundler: 'vite',
        }
    }
})
