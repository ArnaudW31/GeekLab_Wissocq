<script setup>
  import PokeAppBar from './components/PokeAppBar.vue';
</script>

<template>
  <v-app>
    <poke-app-bar></poke-app-bar>
    <div style="height: 30px;"></div>
    <router-view />

    <v-skeleton-loader
      v-if="getLoading"
      type="image"
    />
  </v-app>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

  export default {
    name : "App",
    data() {
      return {
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getUserToken", "getLoading"])
    },
    watch : {
      getUserToken(newVal, oldVal) {
        this.$router.push('/')
      }
    }
  }
</script>

<style></style>
