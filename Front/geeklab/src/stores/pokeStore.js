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
    pageIndex : 0,
    typeColors: {
      fire: 'red lighten-2',
      water: 'blue lighten-2',
      grass: 'green lighten-2',
      electric: 'yellow darken-2',
      poison: 'purple lighten-2',
      psychic: 'pink lighten-2',
      normal: 'grey lighten-2',
      ground: 'brown lighten-2',
      rock: 'grey darken-1',
      bug: 'lime darken-2',
      fairy: 'pink lighten-4',
      fighting: 'deep-orange lighten-2',
      ghost: 'deep-purple lighten-2',
      ice: 'cyan lighten-3',
      dragon: 'indigo darken-2',
      dark: 'grey darken-3',
      steel: 'blue-grey lighten-2',
      flying: 'cyan lighten-2'
    },
    typeBorderColors: {
      fire: '#F08030',
      water: '#6890F0',
      grass: '#78C850',
      electric: '#F8D030',
      poison: '#A040A0',
      psychic: '#F85888',
      normal: '#A8A878',
      ground: '#E0C068',
      rock: '#B8A038',
      bug: '#A8B820',
      fairy: '#EE99AC',
      fighting: '#C03028',
      ghost: '#705898',
      ice: '#98D8D8',
      dragon: '#7038F8',
      dark: '#705848',
      steel: '#B8B8D0',
      flying: '#A890F0'
    }
  }),

  getters: {
    getUserToken: (state) => state.userToken,
    getSelectedTeam : (state) => state.selectedTeam,
    getTeams : (state) => state.shownTeams,
    getLoading : (state) => state.loading,
    getTypeColors : (state) => state.typeColors,
    getTypeBorderColors : (state) => state.typeBorderColors,
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
        const res = await api.get("/teams?page=" + this.pageIndex + "&size="+ pageSize)
        this.shownTeams = res.data;
        console.log(res.data)

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