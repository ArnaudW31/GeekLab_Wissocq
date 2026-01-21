package com.geeklab.api.dto;

import java.util.List;

public class TeamCreateDTO {
    public String name;
    public Long creatorId;
    public List<PokemonDTO> pokemons;

    public TeamCreateDTO(){
        this.name = "";
        this.creatorId = (long) 0;
    }
}
