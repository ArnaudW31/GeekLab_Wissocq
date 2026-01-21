import { createApp } from 'vue'
import { createPinia } from 'pinia'
import vuetify from './plugins/vuetify'
import App from './App.vue'
import router from './router'
import '@mdi/font/css/materialdesignicons.css'

const app = createApp(App)

app.use(createPinia())
    .use(vuetify)
    .use(router)
    .mount('#app')