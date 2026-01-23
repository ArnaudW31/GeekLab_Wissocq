import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import AuthView from '@/views/AuthView.vue'
import TeamView from '@/views/TeamView.vue'
import SignInView from '@/views/SignInView.vue'
import MentionsLegalesView from '@/views/MentionsLegalesView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/auth',
    name: 'auth',
    component: AuthView,
  },
  {
    path: '/team',
    name: 'team',
    component: TeamView,
  },
  {
    path: '/signin',
    name: 'signin',
    component: SignInView,
  },
  {
    path: '/mentions',
    name: 'mentions',
    component: MentionsLegalesView,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
