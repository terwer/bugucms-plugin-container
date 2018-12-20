import Vue from 'vue'
import App from './App.vue'
import vSelectPage from 'v-selectpage';

Vue.config.productionTip = false

Vue.use(vSelectPage, {});

new Vue({
  render: h => h(App),
}).$mount('#app')
