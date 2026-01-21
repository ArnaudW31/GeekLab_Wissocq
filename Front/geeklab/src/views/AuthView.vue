<template>
  <v-container>
    <v-text-field label="Email" v-model="userEmail"></v-text-field>
    <v-text-field label="Mot de passe" v-model="userPassword"></v-text-field>
    <v-btn @click="tryLogin">Connexion</v-btn>
  </v-container>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

  export default {
    name : "AuthView",
    data() {
      return {
        userEmail : "",
        userPassword : "",
      }
    },
    methods : {
      ...mapActions(usePokeStore, ["loginUser"]),
      async tryLogin(){
        await this.loginUser(this.userEmail, this.userPassword)
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getUserToken"])
    }
  }

</script>

<style></style>