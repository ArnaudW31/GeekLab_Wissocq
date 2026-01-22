package com.geeklab.api.dto;

import com.geeklab.api.entity.Pokemon;

public class PokemonDTO {
    public int slotNum;
    public int pokeId;
    public String name;
    public String abilityId;
    public String itemId;
    public String move1Id;
    public String move2Id;
    public String move3Id;
    public String move4Id;
    public boolean isShiny;

    public PokemonDTO(int slotNum, int pokeId, String name, String abilityId,
                   String itemId, String move1Id, String move2Id, String move3Id, String move4Id, boolean isShiny) {
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
        this.abilityId = "";
        this.itemId = "";
        this.move1Id = "";
        this.move2Id = "";
        this.move3Id = "";
        this.move4Id = "";
        this.isShiny = false;
    }
}
