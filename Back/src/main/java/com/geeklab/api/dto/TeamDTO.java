package com.geeklab.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.geeklab.api.entity.Team;

public class TeamDTO {
    public String name;
    public String creatorName;
    public Long voteCount;
    public List<PokemonDTO> pokemons;

    public TeamDTO(){
        this.name = "";
        this.creatorName = "";
        this.voteCount = (long) 0;
    }

    public TeamDTO(Team team){
        this.name = team.getName();
        this.creatorName = team.getCreator().getUsername();
        this.pokemons = new ArrayList<PokemonDTO>();
        this.voteCount = (long)0;
    }
}
