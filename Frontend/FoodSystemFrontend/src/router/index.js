import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      title: '登录'
    },
    component: () => import('../views/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    meta: {
      title: '首页'
    },
    component: () => import('../views/HelloWorld.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})


router.beforeEach((to, from, next) => {
  // 修改页面 title
  if (to.meta.title) {
    document.title = 'FoodSystem - ' + to.meta.title
  }
  // 放行登录页面
  if (to.path === '/login') {
    return next()
  }
  // 获取token
  const token = sessionStorage.getItem('token')
  if (!token) {
    return next('/login')
  } else {
    next()
  }
  return next()
})

// 导出路由
export default router

