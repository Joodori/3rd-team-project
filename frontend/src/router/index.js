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
      path: '/attraction-reservation',
      name: 'attraction_reservation',
      component: () => import('../views/attraction_reservation.vue'),
    },
    {
      path: '/ticket-purchase',
      name: 'ticket_purchase',
      component: () => import('../views/ticket_purchase.vue'),
    },
    {
      path: '/facility-map',
      name: 'facility_map',
      component: () => import('../views/facility_map.vue'),
    },
  ],
})

export default router
