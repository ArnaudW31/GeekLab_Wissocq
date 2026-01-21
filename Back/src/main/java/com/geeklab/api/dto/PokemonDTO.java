package com.geeklab.api.dto;

import com.geeklab.api.entity.Pokemon;

public class PokemonDTO {
    public int slotNum;
    public int pokeId;
    public String name;
    public int abilityId;
    public int itemId;
    public int move1Id;
    public int move2Id;
    public int move3Id;
    public int move4Id;
    public boolean isShiny;

    public PokemonDTO(int slotNum, int pokeId, String name, int abilityId,
                   int itemId, int move1Id, int move2Id, int move3Id, int move4Id, boolean isShiny) {
        this.slotNum = slotNum;
        this.pokeId = pokeId;
        this.name = name;
        this.abilityId = abilityId;
        this.itemId = itemId;
        this.move1Id = move1Id;
        this.move2Id = move2Id;
        this.move3Id = move3Id;
        this.move4Id = move4Id;
        this.isShiny = isShiny;
    }

    public PokemonDTO(Pokemon poke){
        this.slotNum = poke.getId().getSlotNum();
        this.pokeId = poke.getPokeId();
        this.name = poke.getName();
        this.abilityId = poke.getAbilityId();
        this.itemId = poke.getItemId();
        this.move1Id = poke.getMove1Id();
        this.move2Id = poke.getMove2Id();
        this.move3Id = poke.getMove3Id();
        this.move4Id = poke.getMove4Id();    
        this.isShiny = poke.isShiny();
    }

    public PokemonDTO() {
        this.slotNum = 0;
        this.pokeId = 0;
        this.name = "";
        this.abilityId = 0;
        this.itemId = 0;
        this.move1Id = 0;
        this.move2Id = 0;
        this.move3Id = 0;
        this.move4Id = 0;
        this.isShiny = false;
    }
}
