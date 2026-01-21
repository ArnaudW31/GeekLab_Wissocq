<template>
  <v-container class="pa-4">
    
      <v-autocomplete
        v-model="selectedPokemon"
        :items="pokemonListFull"
        item-title="name"
        label="Ajouter un Pokemon"
        clearable
      />

    <v-btn
      icon="mdi-plus"
      color="primary"
      :disabled="!selectedPokemon"
      @click="addPoke"
    />

    <v-card @click="changeCurrentPoke(pkmn)" v-for="pkmn in pokemons">
      <h2>{{ pkmn.name }}</h2>
      <v-img width="150" aspect-ratio="1/1" :src="pkmn.sprites.front_default"></v-img>
    </v-card>

    <v-container v-if="currentPokemon != null">
      <v-img width="300" aspect-ratio="1/1" :src="currentPokemon.sprites.front_default"></v-img>

      <v-autocomplete
        v-model="selectedPokemon"
        :items="pokemonListFull"
        item-title="name"
        label="Changer de Pokémon"
      />

      <v-autocomplete
        v-model="selectedAbility"
        :items="currentPokemon.abilities"
        item-title="ability.name"
        label="Choisir un talent"
        clearable
      />

      <v-autocomplete
        v-model="selectedItem"
        :items="itemListFull"
        item-title="name"
        label="Choisir un objet à tenir"
        clearable
      />

      <v-autocomplete
        v-model="selectedMove1"
        :items="currentPokemonMoveList"
        item-title="name"
        label="Capacité 1"
      />

      <v-autocomplete
        v-model="selectedMove2"
        :items="currentPokemonMoveList"
        item-title="name"
        label="Capacité 2"
      />

      <v-autocomplete
        v-model="selectedMove3"
        :items="currentPokemonMoveList"
        item-title="name"
        label="Capacité 3"
      />

      <v-autocomplete
        v-model="selectedMove4"
        :items="currentPokemonMoveList"
        item-title="name"
        label="Capacité 4"
      />
      
    </v-container>
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
        selectedPokemon : null,
        currentPokemon : null,
        pokemonListFull : [],
        itemListFull : [],
        currentPokemonMoveList : [],
        selectedAbility : null,
        selectedItem : null,
        selectedMove1 : null,
        selectedMove2 : null,
        selectedMove3 : null,
        selectedMove4 : null,
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getFullPokemonList", "getPokeInfo", "getItemList"])
    },
    async mounted(){
      this.pokemonListFull = await this.getFullPokemonList()
      this.itemListFull = await this.getItemList();
    },
    methods: {
      async addPoke(){
        if (this.pokemons.length == 6){
          return
        }

        let infosPoke = await this.getPokeInfo(this.selectedPokemon)

        this.pokemons.push(infosPoke)

        this.selectedPokemon = null

        this.currentPokemonMoveList = infosPoke.moves.map(m => m.move)

        console.log(infosPoke.sprites.front_default)
      },
      changeCurrentPoke(pkmn){
        this.currentPokemon = pkmn
      }
    }
  }
</script>

<style></style>