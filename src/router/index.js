import Vue from 'vue'
import Router from 'vue-router'
import errMsg from '@/components/error.vue'
import car from '@/components/car.vue'
import guiji from '@/components/guiji.vue'
import xiangyu from '@/components/xiangyu.vue'
import index from '@/components/index.vue'
import guize from '@/components/guize.vue'

Vue.use(Router)

export default new Router({
  routes: [
      {
        path:'/',
      },
      {
        path: '/index',
        name: 'index',
        component: index
      },
      {
        path: '/car',
        name: 'car',
        component: car
      },
      {
        path: '/guiji',
        name: 'guiji',
        component: guiji
      },
      {
        path: '/xiangyu',
        name: 'xiangyu',
        component: xiangyu
      },
      {
        path: '/errMsg',
        name: 'errMsg',
        component: errMsg
      },
      {
        path: '/guize',
        name: 'guize',
        component: guize
      }
  ],
  mode: 'history'
})
