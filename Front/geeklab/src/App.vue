<script setup>
  import PokeAppBar from './components/PokeAppBar.vue';
</script>

<template>
  <v-app class="d-flex flex-column min-vh-100">
    <poke-app-bar></poke-app-bar>
    <div style="height: 50px;"></div>

    <v-main class="flex-grow-1">
      <router-view />
    </v-main>

    <v-skeleton-loader
      v-if="getLoading"
      type="image"
    />
    <poke-footer class="limitHeight"></poke-footer>
  </v-app>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';
  import PokeFooter from './components/PokeFooter.vue';

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

<style>

.limitHeight{
  max-height: 60px;
  bottom: 0;
}
</style>
