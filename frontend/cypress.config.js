const {defineConfig} = require('cypress')
const {Client} = require('pg')
const {DB} = require("./cypress.env.json")

module.exports = defineConfig({
    e2e: {
        baseUrl: 'http://localhost:5172/',
        viewportHeight: 1080,
        viewportWidth: 1920,
        defaultCommandTimeout: 10000,
        setupNodeEvents(on, config) {
            on('before:browser:launch', (browser = {}, launchOptions) => {
                // @ts-ignore
                if (browser.family === 'chromium') {
                    // Add localhost:8080, the Firestore emulator host:port when running locally, to the Chrome proxy bypass
                    // So Cypress doesn't jack with it
                    launchOptions.args.push(
                            '--proxy-bypass-list=<-loopback>,localhost:8484'
                    )
                }
                return launchOptions
            })
            on("task", {
                dbQuery: async (query) => {
                    const client = new Client(DB)
                    await client.connect()
                    const res = await client.query(query)
                    await client.end()
                    return res
                }
            })
        },
        fixturesFolder: false,
        supportFile: 'cypress/support/index.ts',
        specPattern: 'cypress/e2e/**/*.cy.ts',
        videosFolder: 'cypress/videos',
        videoCompression: false,
        screenshotsFolder: 'cypress/screenshots',
        experimentalInteractiveRunEvents: true
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
