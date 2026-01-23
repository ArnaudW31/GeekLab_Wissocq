<template>
  <v-container class="pa-4">

    <h1 class="mb-4">Liste des équipes Pokémon</h1>

    <v-row>
      <v-col
        v-for="(team, index) in teams" :key="index" cols="12" md="6">
        <v-card class="pa-4 mb-4 elevation-4">
          <div class="d-flex justify-space-between align-center mb-2">
            <h2>{{ team.name }}</h2>
            <span class="grey--text text--darken-1">Créateur : {{ team.creatorName }}</span>
          </div>

          <!-- Votes -->
          <v-chip color="yellow darken-2" text-color="black" class="mb-2">
            Votes : {{ team.voteCount }}
          </v-chip>

          <!-- Pokémon de l'équipe -->
          <v-row class="mt-2">
            <v-col
              v-for="(pkmn, idx) in team.pokemons"
              :key="idx"
              cols="4"
              sm="3"
              md="2"
            >
              <v-card class="pa-2 text-center" elevation="2">
                <v-img
                  width="100"
                  aspect-ratio="1"
                  :src="pkmn.isShiny ? getShinySprite(pkmn.pokeId) : getDefaultSprite(pkmn.pokeId)"
                />
                <div class="mt-1">{{ pkmn.name }}</div>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';

export default {
  name: "TeamListPage",
  data() {
    return {
      teams: [] // chargement via API
    };
  },
  async mounted() {
    // Exemple : récupération depuis ton API
    this.teams = await this.fetchTeams();
  },
  computed: {
    ...mapState(usePokeStore, ["getTypeColors", "getTypeBorderColors", "getUserToken", "getTeams"])
  },
  methods: {
    ...mapActions(usePokeStore, [ "getPokeInfo", "updateTeamList"]),
    async fetchTeams() {
      
      await this.updateTeamList()

      return this.getTeams
    },
    getDefaultSprite(pokeId) {
      return `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeId}.png`;
    },
    getShinySprite(pokeId) {
      return `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokeId}.png`;
    }
  }
};
</script>

<style>
/* Optionnel : style léger pour les cartes Pokémon */
</style>
