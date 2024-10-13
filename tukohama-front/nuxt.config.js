export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title : 'CrespoVale - Portal de Noticias',
    htmlAttrs: {
      lang: 'sp'
    },
    noscript: [
      { innerHTML: 'Este sitio no funciona correctamente sin JavaScript.' }
    ],
    meta: [
      // "./assets/css/estilo.css";
      { charset: 'utf-8' },
      { 'http-equiv': 'cache-control', content:'no-cache'},
      { 'http-equiv': 'X-UA-Compatible', content:'IE=edge'},
      { name: 'viewport', content: 'width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' },
      { property: "og:title", content: "CrespoVale - Portal de Noticias" },
      { property: "og:type", content: "website" },
      { property: "og:url", content: "https://www.crespovale.com.ar/" },
      { property: "og:description", content: "Portal de noticias para Crespo y zona." },
      { property: "og:image", content: "/logo.png" },
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Baloo+2|Baloo+Chettan+2|Lato|Roboto|Material+Icons|Ubuntu&display=swap' },
      { rel: 'stylesheet', href: 'https://kit-free.fontawesome.com/releases/latest/css/free.min.css' },
    ],
    style: [
    ],
    script: [
      // GOOGLE TAG MANAGER
    ],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    '@/assets/css/estilo.css',
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '~/plugins/vue-paginate.js',
    '~/plugins/vue2-editor.js',
    '~/plugins/vue-toast.js',
    '~/plugins/vue-core-video-player.js',
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
    // https://go.nuxtjs.dev/vuetify
    '@nuxtjs/vuetify',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
  },

  // Vuetify module configuration: https://go.nuxtjs.dev/config-vuetify
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  },

  env: {
    baseApiURL: process.env.NODE_ENV === 'dev' ? 'http://localhost:9088/api/' : 'https://crespovale.com.ar:9088/api/',
    baseAppURL: process.env.NODE_ENV === 'dev' ? 'http://localhost:8080' : 'https://crespovale.com.ar'

  }, 

}