<template>
  <v-container class="pa-4">
    <v-autocomplete
      v-model="selectedPokemon"
      :items="pokemonListFull"
      item-title="name"
      label="Choisir un Pokemon"
      clearable
    />

    <v-btn
      icon="mdi-plus"
      color="primary"
      :disabled="!selectedPokemon"
      @click="addPoke"
    />

    <v-select
      class="mt-4"
      v-model="currentPokemon"
      :items="pokemons"
      item-title="name"
      label="Pokemons de l'équipe"
    />
  </v-container>
</template>
<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

  export default {
    name : "TeamView",
    data() {
      return {
        pokemons : [],
        selectedPokemon : [],
        currentPokemonIndex : 0,
        currentPokemon : {},
        pokemonListFull : [],
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getFullPokemonList", "getPokeInfo"])
    },
    async mounted(){
      this.pokemonListFull = await this.getFullPokemonList()
    },
    methods: {
      async addPoke(){
        if (this.pokemons.length == 6){
          return
        }

        let infosPoke = await this.getPokeInfo(this.selectedPokemon)

        this.pokemons.push(infosPoke)

        this.selectedPokemon = null

        this.currentPokemon = this.pokemons[this.pokemons.length]
      }
    }
  }
</script>

<style></style>