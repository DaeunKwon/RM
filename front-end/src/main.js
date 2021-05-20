import Vue from 'vue'
import Vuex from 'vuex';
import './plugins/axios'
import App from './App.vue'
import store from './store'
import router from './router/index.js'
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import vueMoment from 'vue-moment'

Vue.use(Vuex)
Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(Vuetify)
Vue.use(vueMoment)

new Vue({
  render: h => h(App),
  vuetify: new Vuetify(),
  router,
  store,
}).$mount('#app')
