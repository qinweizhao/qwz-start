import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import axios from '../axios'
import store from '../store'

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        children: [
            {
                path: '/index',
                name: 'Index',
                meta: {title: "首页"},
                component: () => import('@/views/Index.vue')
            },
            {
                path: '/userCenter',
                name: 'UserCenter',
                meta: {
                    title: "个人中心"
                },
                component: () => import('@/views/UserCenter.vue')
            },
        ]
    }
]


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {

    let hasRoutes = store.state.menus.hasRoutes

    let token = localStorage.getItem("token");

    if (to.path == '/login') {
        console.log("login!!!!!!");
        next()
    } else if (!token) {
        console.log("token不存在");
        next({path: '/login'})
        //next()
    } else if (token && !hasRoutes) {
        axios.get("/sys/menu/nav", {
            headers: {
                Authorization: localStorage.getItem("token")
            }
        }).then(res => {
            console.log(res.data.data);

            store.commit("setMenuList", res.data.data.nav);

            store.commit("setPermList", res.data.data.authoritys);

            // 动态绑定路由
            let newRoutes = router.options.routes;

            res.data.data.nav.forEach(menu => {
                if (menu.children) {
                    menu.children.forEach(e => {
                        // 转成路由
                        let route = menuToRoute(e);
                        // 把路由添加到路由管理中
                        if (route) {
                            newRoutes[1].children.push(route)
                        }
                    })
                }
            })

            console.log("newRoutes")
            console.log(newRoutes)

            router.addRoutes(newRoutes)
            hasRoutes = true
            store.commit("changeRouteStatus", hasRoutes)
        })
    }


    next()

})


// 导航转成路由
const menuToRoute = (menu) => {
    console.log("正在添加menu--》")
    console.log(menu)
    if (!menu.component) {
        return null
    }
    // 复制属性  
    let route = {
        name: menu.menuName,
        path: menu.path,
        meta: {
            icon: menu.icon,
            title: menu.menuName
        }
    }
    route.component = () => import('@/views/' + menu.component + '.vue')
    return route
}


export default router
