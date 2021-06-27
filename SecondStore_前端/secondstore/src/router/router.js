import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [

  //商城系统路由信息
  {
    path: '/',
    component:() => import('../views/Home.vue'),
    redirect:{  //重定向
      name: 'home'
    },
    children:[
      {
        name: 'home',
        path: 'home',
        meta: {
          title: '商城首页'
        },
        component: () => import('../views/HomePage.vue')
      },
      {
        path: 'kind/:id',
        name: 'category',
        meta: {
          title: '分类商品'
        },
        component: () => import('../views/Commodities.vue')
      },
      {
        path: 'searchOrder',
        name: 'searchOrder',
        meta: {
          title: '搜索结果'
        },
        component: () => import('../views/Commodities.vue')
      },
      {
        path: 'commodity/:id',
        name: 'commodity',
        meta:{
          title: '商品信息'
        },
        component: ()=>import('../views/Commodity.vue')
      },
      {
        path: 'cart',
        name: 'cart',
        meta: {
          title: '购物车',
          requiresAuth: true
        },
        component: () => import('../views/ShoppingCart.vue')
      },
      {
        path:'account',
        name: 'account',
        meta: {
          title: '我的账户',
          requiresAuth: true
        },
        component: ()=>import('../views/Account.vue')
      },
      {
        path: 'check',
        name: 'check',
        meta: {
          title: '结算',
          requiresAuth: true
        },
        component: () => import('../views/Checkout.vue')
      },
      {
        path: 'order',
        name: 'order',
        meta: {
          title: '我的订单',
          requiresAuth: true
        },
        component: () => import('../views/Order.vue')
      },
    ]
  },


  {
    path: '/login',
    name: 'login',
    meta: {
      title: '登录'
    },
    component: ()=>import('../components/UserLogin.vue')
  },
  {
    path: '/register',
    name: 'register',
    meta:{
      title: '注册'
    },
    component: ()=>import('../components/UserRegister.vue')
  },
  {
    path: '/sellRegister',
    name: 'sellRegister',
    meta:{
      title: '商家注册'
    },
    component: ()=>import('../components/SellRegister.vue')
  },


  // 商家系统路由信息
  {
    path: '/shop',
    component: ()=>import('../views/sell/SellHome.vue'),
    children:[
      {
        path:'shopId/:id',
        name:'shop',
        meta:{
          title:'商家系统'
        },
        component: ()=>import('../views/sell/Shop.vue')
      },
      {
        path:'info',
        name:'sellInfo',
        meta:{
          title:'商家系统'
        },
        component: ()=>import('../views/sell/SellInfo.vue')
      },
      {
        path:'order',
        name:'sellOrder',
        meta:{
          title:'我的订单'
        },
        component: ()=>import('../views/sell/SellOrder.vue')
      },
      {
        path:'commodity/:id',
        name:'shopCommodity',
        meta:{
          title:'商品详情'
        },
        component: ()=>import('../views/Commodity.vue')
      },
    ]
  },


  // 管理员系统路由信息
  {
    path: '/admin',
    redirect:{
      name: 'userAudit'
    },
    meta:{
      title: '管理员系统'
    },
    component: ()=>import('../views/admin/AdminHome.vue'),
    children:[
      {
        path:'bargain',
        name:'bargain',
        meta:{
          title:'交易管理'
        },
        component:()=>import('../views/admin/BargainManagement.vue')
      },
      {
        path:'audit/commodity',
        name:'commodityAudit',
        meta:{
          title:'商品审核'
        },
        component:()=>import('../views/admin/CommodityAudit.vue')
      },
      {
        path:'audit/user',
        name:'userAudit',
        meta:{
          title:'用户审核'
        },
        component:()=>import('../views/admin/UserAudit.vue')
      },
      {
        path:'recharge',
        name:'recharge',
        meta:{
          title:'充值管理'
        },
        component:()=>import('../views/admin/RechargeManagement.vue')
      },
      {
        path:'sellRate',
        name:'sellRate',
        meta:{
          title:'商家等级管理'
        },
        component:()=>import('../views/admin/SellRateManagement.vue')
      },
    ]
  }
]


const router = new Router({
  routes
})


//全局前置守卫
router.beforeEach((to, from, next) => {
  // 判断该路由是否需要登录权限
  if (to.matched.some(record => record.meta.requiresAuth))
  {
    // 路由需要验证，判断用户是否已经登录
    if(sessionStorage.getItem('user') || sessionStorage.getItem('sell') || sessionStorage.getItem('admin')){
      next(); 
    }
    else{
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      });
    }
  }
  else
    next();
})

// 通过全局后置守卫设置页面的标题
router.afterEach((to) => {
  document.title = to.meta.title;
})

export default router

