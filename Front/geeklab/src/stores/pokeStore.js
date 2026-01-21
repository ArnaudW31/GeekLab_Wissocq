import { defineStore } from 'pinia'
import { api } from '@/plugins/api'

import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const pageSize = 20;

export const usePokeStore = defineStore('poke', {
  state: () => ({
    userToken: "",
    loading : false,
    selectedTeam : {},
    shownTeams : [],
    pageIndex : 0
  }),

  getters: {
    getUserToken: (state) => state.userToken,
    getSelectedTeam : (state) => state.selectedTeam,
    getTeams : (state) => state.shownTeams,
  },

  actions: {
    async loginUser(userEmail, userPassword) {
      try {
        this.loading = true
        const res = await api.post("/auth/login", { "email" : userEmail, "password" : userPassword })
        console.log(res.data)
        this.userToken = res.data;

        toast.success("Connexion réussie", {
          autoClose: 1000,
        });

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
        toast.warn("Mauvais identifiant", {
          autoClose: 1000,
        });
      }
      finally {
        this.loading = false
      }
    },

    async createUser(userName, userEmail, userPassword) {
      try {
        this.loading = true
        const res = await api.post("/auth/register", {"username": userName, "email" : userEmail, "password" : userPassword })
        console.log(res.data)
        this.userToken = res.data;
        toast.success("Connexion réussie", {
          autoClose: 1000,
        });
      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
        toast.warn("Erreur lors de l'inscription", {
          autoClose: 1000,
        });
      }
      finally {
        this.loading = false
      }
    },

    logoutUser(){
      this.userToken = "";
      toast.success("Déconnexion réussie", {
        autoClose: 1000,
      });
    },

    updateSelectedTeam(newTeam) {
      this.selectedTeam = newTeam
    },

    async updateTeamList(){
      try {
        this.loading = true
        const res = await api.get("/teams?page=${this.pageIndex}&size=${pageSize}")
        console.log(res.data)
        this.shownTeams = res.data;

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
      }
      finally {
        this.loading = false
      }
    },

    async createTeam(teamData){
      try {
        this.loading = true
        const res = await api.post("/teams", teamData)
        console.log(res.data)

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
      }
      finally {
        this.loading = false
      }
    },

    async getFullPokemonList(){
      try {
        this.loading = true
        const res = await api.get("/poke/pokemon")

        console.log(res.data)
        return res.data.results;

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
      }
      finally {
        this.loading = false
      }
    },

    async getPokeInfo(name){
      try {
        this.loading = true
        const res = await api.get("/poke/pokemon/" + name)

        console.log(res.data)
        return res.data;

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
      }
      finally {
        this.loading = false
      }
    },

    async getItemList(){
      try {
        this.loading = true
        const res = await api.get("/poke/items")

        console.log(res.data)
        return res.data;

      } catch (err) {
        console.error(err.response?.status)
        console.error(err.response?.data)
      }
      finally {
        this.loading = false
      }
    },

    changePage(increment){
      this.pageIndex += increment
    }
  }
})