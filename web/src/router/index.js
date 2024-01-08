import { createRouter, createWebHistory } from 'vue-router'
// import MainView from '../views/MainView.vue'
import store from "@/store";

const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: MainView,
  //   meta: {
  //     loginRequire: true  //必须登录
  //   }
  // },
  {
    path: '/',
    component: () => import('../views/MainView.vue'),
    meta: {
      loginRequire: true
    },
    children: [{
      path: 'welcome',
      component: () => import('../views/main/WelcomeView.vue'),
    }
    // }, {
    //   path: 'passenger',
    //   component: () => import('../views/main/passenger.vue'),
    // }, {
    //   path: 'ticket',
    //   component: () => import('../views/main/ticket.vue'),
    // }, {
    //   path: 'order',
    //   component: () => import('../views/main/order.vue'),
    // }]
    ]
  }
    ,
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path:"",
    redirect:'/welcome'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由跳转拦截
router.beforeEach((to, from, next) => {
  //要不要对meta.loginRequire属性做监控拦截
  if(to.matched.some(function (item) {
    console.log(item, "是否需要邓丽校验：", item.meta.loginRequire || false);
    return item.meta.loginRequire;
  })) {
    const user = store.state.member;
    console.log("页面登录校验开始：", user);
    if(!user.token) {
      console.log("用户未登录或登录超时!");
      next("/login");
    } else {
      next();
    }
  } else {
    next();
  }
})

export default router
