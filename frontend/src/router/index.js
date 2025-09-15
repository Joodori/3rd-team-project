import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login.vue'),
    },
    {
      path: '/sign-up',
      name: 'sign_up',
      component: () => import('../views/sign_up.vue'),
    },
    {
      path: '/user-info',
      name: 'user_info',
      component: () => import('../views/user_info.vue'),
    },
    {
      path: '/attraction_reservation',
      name: 'attraction_reservation',
      component: () => import('../views/attraction_reservation.vue'),
    },
    {
      path: '/ticket_purchase',
      name: 'ticket_purchase',
      component: () => import('../views/ticket_purchase.vue'),
    },
    /*
    {
      path: '/ride_faclity_map.vue',
      name: 'ride_faclity_map',
      component: () => import('../views/ride_faclity_map.vue'),
    },
    */
    {
      path: '/admin_reservation', //관리자 예약 하는거 뷰화면 만들기 
      name: 'admin_reservation',
      component: () => import('../views/admin_reservation.vue'),
    },
    /*
    {
      path: '/event', //인구 밀도 히트맵 
      name: 'event',
      component: () => import('../views/event.vue'),
    },
    
    {
      path: '/ride_faclity_map', //인구 밀도 히트맵
      name: 'ride_faclity_map',
      component: () => import('../views/ride_faclity_map.vue'),
    },
*/
  ],
})

export default router
