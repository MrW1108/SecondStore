import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store/store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueAxios from 'vue-axios'  //安装插件
import axios from 'axios';
import msgBox from './plugin/vue-msgbox'
import { currency, factPrice } from './utils/filters'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(msgBox)
Vue.use(VueAxios, axios)
axios.defaults.baseURL = "/api"
Vue.prototype.HOST = "/api"
Vue.filter('currency', currency)
Vue.filter('factPrice', factPrice)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
