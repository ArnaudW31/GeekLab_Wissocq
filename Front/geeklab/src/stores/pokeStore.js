import { defineStore } from 'pinia'
import { api } from '@/plugins/api'

export const usePokeStore = defineStore('poke', {
  state: () => ({
    count: 0
  }),

  getters: {
    doubleCount: (state) => state.count * 2
  },

  actions: {
    increment() {
      this.count++
    }
  }
})