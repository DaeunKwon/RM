import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router/index.js'
import vuetify from 'vuetify';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(BootstrapVue)

new Vue({
  render: h => h(App),
  vuetify,
  router
}).$mount('#app')
