/** @type {import('tailwindcss').Config} */

module.exports = {
    content: [
        "./index.html",
        "./src/**/*.{vue,ts}",
        "./node_modules/vue-tailwind-datepicker/**/*.ts"
    ],
    theme: {
        extend: {}
    },
}

/* Original config:
module.exports = {
    content: [
        './index.html',
        './src/!**!/!*.{vue,js}'
    ],
    theme: {
        extend: {}
    }
}
*/
/* Config directly from
const colors = require("tailwindcss/colors")
module.exports = {
    content: [
        "./index.html",
        "./src/!**!/!*.{vue,js,ts,jsx,tsx}",
        "./node_modules/vue-tailwind-datepicker/!**!/!*.js"
    ],
    theme: {
        extend: {
            colors: {
                "vtd-primary": colors.sky, // Light mode Datepicker color
                "vtd-secondary": colors.gray, // Dark mode Datepicker color
            },
        },
    },
    plugins: [
        require('@tailwindcss/forms'),
    ]
}*/

