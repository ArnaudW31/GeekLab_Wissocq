<template>
  <v-app-bar
    color="primary"
    dark
    app
    elevate-on-scroll
  >
    <!-- Titre -->
    <v-app-bar-title class="text-h6">
      GeekLab Pokémon
    </v-app-bar-title>

    <v-spacer></v-spacer>

    <!-- Boutons toujours visibles -->
    <v-btn text to="/">Accueil</v-btn>

    <!-- Connexion / Inscription -->
    <template v-if="getUserToken === ''">
      <v-btn text to="/auth">Connexion</v-btn>
      <v-btn text to="/signin">Inscription</v-btn>
    </template>

    <!-- Utilisateur connecté -->
    <template v-else>
      <v-btn text to="/team">Mes équipes</v-btn>
      <v-btn text @click="logoutUser">Se déconnecter</v-btn>
    </template>
  </v-app-bar>
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