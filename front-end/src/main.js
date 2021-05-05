import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router/index.js'
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(Vuetify);

new Vue({
  render: h => h(App),
  vuetify: new Vuetify(),
  router
}).$mount('#app')

