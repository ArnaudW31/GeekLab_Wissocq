<template>
  <v-container>
    <v-app-bar>
      <v-app-bar-title>Bienvenue sur GeekLab</v-app-bar-title>
      <v-btn to="/">Accueil</v-btn>
      <v-container v-if="getUserToken === ''">
        <v-btn to="/auth" >Connexion</v-btn>
        <v-btn to="/signin" >Inscription</v-btn>
      </v-container>
      <v-container v-else>
        <v-btn to="/team" >Mes équipes</v-btn>
        <v-btn @click="logoutUser" >Se déconnecter</v-btn>
      </v-container>
    </v-app-bar>
  </v-container>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

  export default {
    name : "PokeAppBar",
    data() {
      return {
      }
    },
    methods : {
      ...mapActions(usePokeStore, ["loginUser", "logoutUser"])
    },
    computed: {
      ...mapState(usePokeStore, ["getUserToken"])
    }
  }
</script>

<style></style>