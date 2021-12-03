import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'

import request from './util/axios'


Vue.config.productionTip = false;

Vue.use(ElementUI);

Vue.prototype.$axios = request;

Vue.mixin({
	methods: {
		hasAuthority(perm) {
			var authority = this.$store.state.menus.permList
			return authority.indexOf(perm) > -1
		}
	}
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
