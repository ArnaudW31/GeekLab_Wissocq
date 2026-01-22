<template>
  <v-container class="pa-4">

    <!-- NOM DE L'ÉQUIPE -->
    <v-card class="pa-4 mb-4">
      <v-text-field
        v-model="teamName"
        label="Nom de l'équipe"
      />
    </v-card>

    <!-- AJOUT POKÉMON -->
    <v-card v-if="pokemons.length < 6" class="pa-4 mb-4">
      <v-row align="center">
        <v-col cols="10">
          <v-autocomplete
            v-model="selectedPokemon"
            :items="pokemonListFull"
            item-title="name"
            label="Ajouter un Pokémon"
            clearable
          />
        </v-col>

        <v-col cols="2" class="text-center">
          <v-btn
            icon="mdi-plus"
            color="primary"
            :disabled="!selectedPokemon"
            @click="addPoke"
          />
        </v-col>
      </v-row>
    </v-card>

    <!-- ÉQUIPE -->
    <v-row class="mb-4">
      <v-col
        v-for="(pkmn, index) in pokemons"
        :key="index"
        cols="6"
        sm="4"
        md="2"
      >
        <v-card
          class="text-center pa-2 type-border"
          @click="changeCurrentPoke(index)"
          :style="getTypeBorderStyle(pkmn)"
        >
          <v-img
            width="120"
            aspect-ratio="1"
            :src="pokemonData[index].isShiny
              ? pkmn.sprites.front_shiny
              : pkmn.sprites.front_default"
          />
          <strong>{{ pkmn.name }}</strong>
        </v-card>
      </v-col>
    </v-row>

    <!-- POKÉMON SÉLECTIONNÉ -->
    <v-card v-if="currentPokemon" class="pa-4">
      <v-row>
        <v-col cols="12" md="4" class="text-center">
          <v-img
            width="250"
            aspect-ratio="1"
            :src="selectedShiny
              ? currentPokemon.sprites.front_shiny
              : currentPokemon.sprites.front_default"
          />

          <v-chip-group class="mt-2" column :model-value="null" mandatory="false" :ripple="false">
            <v-chip
              v-for="type in currentPokemon.types"
              :key="type.type.name"
              :color="typeColors[type.type.name]"
              variant="elevated"
              :ripple="false"
              :clickable="false"
            >
              {{ type.type.name }}
            </v-chip>
          </v-chip-group>

          <v-text-field
            v-model="selectedName"
            label="Nom du Pokémon"
            class="mt-2"
          />

          <v-checkbox
            v-model="selectedShiny"
            label="Shiny"
          />
        </v-col>

        <v-col cols="12" md="8">
          <v-autocomplete
            v-model="selectedNewPoke"
            :items="pokemonListFull"
            item-title="name"
            label="Changer de Pokémon"
            @update:model-value="onChangePoke"
          />

          <v-autocomplete
            v-model="selectedAbility"
            :items="currentPokemon.abilities"
            item-title="ability.name"
            label="Talent"
            clearable
            @update:model-value="onChangeAbility"
          />

          <v-autocomplete
            v-model="selectedItem"
            :items="itemListFull"
            item-title="name"
            label="Objet tenu"
            clearable
            @update:model-value="onChangeItem"
          />

          <v-divider class="my-4" />

          <h3 class="mb-2">Capacités</h3>

          <v-row>
            <v-col cols="12" sm="6">
              <v-autocomplete
                v-model="selectedMove1"
                :items="currentPokemonMoveList"
                item-title="name"
                label="Capacité 1"
                @update:model-value="onChangeMove1"
              />
            </v-col>

            <v-col cols="12" sm="6">
              <v-autocomplete
                v-model="selectedMove2"
                :items="currentPokemonMoveList"
                item-title="name"
                label="Capacité 2"
                @update:model-value="onChangeMove2"
              />
            </v-col>

            <v-col cols="12" sm="6">
              <v-autocomplete
                v-model="selectedMove3"
                :items="currentPokemonMoveList"
                item-title="name"
                label="Capacité 3"
                @update:model-value="onChangeMove3"
              />
            </v-col>

            <v-col cols="12" sm="6">
              <v-autocomplete
                v-model="selectedMove4"
                :items="currentPokemonMoveList"
                item-title="name"
                label="Capacité 4"
                @update:model-value="onChangeMove4"
              />
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-card>

    <v-btn text="Créer l'équipe" @click="registerTeam" color="blue" size="large"></v-btn>
  </v-container>
</template>
<script>
  import { usePokeStore } from '@/stores/pokeStore'
  import { mapActions, mapState } from 'pinia';
  import { toast } from 'vue3-toastify';
  import 'vue3-toastify/dist/index.css';

  export default {
    name : "TeamView",
    data() {
      return {
        pokemons : [],
        pokemonData : [],
        teamName : "",
        selectedPokemon : null,
        currentPokemonIndex : null,
        currentPokemon : null,
        pokemonListFull : [],
        itemListFull : [],
        currentPokemonMoveList : [],
        selectedNewPoke : null,
        selectedAbility : null,
        selectedItem : null,
        selectedMove1 : null,
        selectedMove2 : null,
        selectedMove3 : null,
        selectedMove4 : null,
        selectedShiny : false,
        selectedName : null,
        typeColors : {},
      }
    },
    watch:{
      selectedShiny(){
        this.pokemonData[this.currentPokemonIndex].isShiny = this.selectedShiny
      },
      selectedName(){
        this.pokemonData[this.currentPokemonIndex].name = this.selectedName
      }
    },
    computed: {
      ...mapState(usePokeStore, ["getTypeColors", "getTypeBorderColors", "getUserToken"])
    },
    async mounted(){
      this.pokemonListFull = await this.getFullPokemonList()
      this.itemListFull = await this.getItemList();
      this.typeColors = this.getTypeColors;
    },
    methods: {
      ...mapActions(usePokeStore, ["getFullPokemonList", "getPokeInfo", "getItemList", "createTeam"]),
      async addPoke(){
        if (this.pokemons.length == 6){
          return
        }

        let infosPoke = await this.getPokeInfo(this.selectedPokemon)

        this.selectedNewPoke = this.selectedPokemon

        this.pokemons.push(infosPoke)

        this.pokemonData.push({id : infosPoke.id})

        this.selectedPokemon = null

        this.currentPokemonMoveList = infosPoke.moves.map(m => m.move)
      },
      changeCurrentPoke(pkmnIndex){
        this.currentPokemon = this.pokemons[pkmnIndex]
        this.currentPokemonIndex = pkmnIndex

        this.selectedNewPoke = this.currentPokemon

        this.selectedName = this.pokemonData[this.currentPokemonIndex].name ?? this.currentPokemon.name
        this.selectedAbility = this.pokemonData[this.currentPokemonIndex].ability
        this.selectedItem = this.pokemonData[this.currentPokemonIndex].item
        this.selectedMove1 = this.pokemonData[this.currentPokemonIndex].move1
        this.selectedMove2 = this.pokemonData[this.currentPokemonIndex].move2
        this.selectedMove3 = this.pokemonData[this.currentPokemonIndex].move3
        this.selectedMove4 = this.pokemonData[this.currentPokemonIndex].move4
        this.selectedShiny = this.pokemonData[this.currentPokemonIndex].isShiny

      },
      async onChangePoke(pokemon) {
        this.currentPokemon = null
        this.error = null

        if (!pokemon) {
          return
        }

        this.selectedAbility = null
        this.selectedItem = null
        this.selectedMove1 = null
        this.selectedMove2 = null
        this.selectedMove3 = null
        this.selectedMove4 = null

        try {
          const infos = await this.getPokeInfo(pokemon)

          this.pokemonData[this.currentPokemonIndex] = {name : infos.name}

          this.pokemons[this.currentPokemonIndex] = infos

          this.currentPokemon = infos
        } catch (e) {
          this.error = 'Erreur lors du chargement du Pokémon'
        }
      },
      onChangeAbility(value){
        this.pokemonData[this.currentPokemonIndex].ability = value
      },
      onChangeItem(value){
        this.pokemonData[this.currentPokemonIndex].item = value
      },
      onChangeMove1(value){
        this.pokemonData[this.currentPokemonIndex].move1 = value
      },
      onChangeMove2(value){
        this.pokemonData[this.currentPokemonIndex].move2 = value
      },
      onChangeMove3(value){
        this.pokemonData[this.currentPokemonIndex].move3 = value
      },
      onChangeMove4(value){
        this.pokemonData[this.currentPokemonIndex].move4 = value
      },
      onChangeName(value){
        this.pokemonData[this.currentPokemonIndex].name = value
      },
      getTypeBorderStyle(pokemon) {
        
        let color1 = this.getTypeBorderColors[pokemon.types[0]?.type.name] || 'grey'
        let color2 = this.getTypeBorderColors[pokemon.types[1]?.type.name] || color1

        return {
          border: '4px solid transparent',
          borderImage: `linear-gradient(to right, ${color1}, ${color2}) 1`,
          borderRadius: '12px'
        }
      },
      registerTeam(){
        let teamData = {}

        if (this.pokemonData.length == 0){
          toast.warn("Pas de pokémon dans l'équipe", {
              autoClose: 5000,
          });
          return
        }

        if (this.teamName == ""){
          toast.warn("Pas de nom d'équipe", {
              autoClose: 5000,
          });
          return
        }

        if (this.getUserToken.id){
          teamData.name = this.teamName
          teamData.creatorId = this.getUserToken.id
        }
        else{
          toast.warn("Vous n'etes pas connecté, impossible de créer l'équipe", {
            autoClose: 5000,
          });
          return
        }

        teamData.pokemons = []
        for(let i = 0; i < this.pokemonData.length; i++){
          let poke = this.pokemonData[i]
          let teamMember = {}
          
          if (poke.name != "" && poke.ability != null && poke.move1 != null){
            teamMember.slotNum = i
            teamMember.pokeId = poke.id
            teamMember.name = poke.name
            teamMember.abilityId = poke.ability
            teamMember.itemId = poke.item
            teamMember.move1Id = poke.move1
            teamMember.move2Id = poke.move2
            teamMember.move3Id = poke.move3
            teamMember.move4Id = poke.move4
            teamMember.isShiny = poke.isShiny
          }
          else{
            toast.warn("Erreur dans le pokémon " + i, {
              autoClose: 5000,
            });
            return
          }
          teamData.pokemons.push(teamMember)
        }

        console.log(teamData)

        this.createTeam(teamData)
      }
    }
  }
</script>

<style></style>