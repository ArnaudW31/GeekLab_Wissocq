<template>
  <v-container>
    <v-text-field label="Nom d'utilisateur" v-model="userName"></v-text-field>
    <v-text-field label="Email" v-model="userEmail"></v-text-field>
    <v-text-field label="Mot de passe" v-model="userPassword"></v-text-field>
    <v-btn @click="trySignIn">Inscription</v-btn>
  </v-container>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

  export default {
    name : "SignInView",
    data() {
      return {
        userName : "",
        userEmail : "",
        userPassword : "",
      }
    },
    methods : {
      ...mapActions(usePokeStore, ["createUser"]),
      async trySignIn(){
        await this.createUser(this.userName, this.userEmail, this.userPassword)
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getUserToken"])
    }
  }

</script>

<style></style>