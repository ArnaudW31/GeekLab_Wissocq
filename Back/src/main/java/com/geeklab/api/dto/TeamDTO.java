package com.geeklab.api.dto;

import java.util.Optional;

import com.geeklab.api.entity.Team;

public class TeamDTO {
    public String name;
    public String creatorName;
    public Long voteCount;
    public Optional<PokemonDTO> pokemon1;
    public Optional<PokemonDTO> pokemon2;
    public Optional<PokemonDTO> pokemon3;
    public Optional<PokemonDTO> pokemon4;
    public Optional<PokemonDTO> pokemon5;
    public Optional<PokemonDTO> pokemon6;

    public TeamDTO(){
        this.name = "";
        this.creatorName = "";
        this.voteCount = (long) 0;
    }

    public TeamDTO(Team team){
        this.name = team.getName();
        this.creatorName = team.getCreator().getUsername();
        this.voteCount = (long)0;
    }
}
