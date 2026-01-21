package com.geeklab.api.dto;

import java.util.Optional;

public class TeamCreateDTO {
    public String name;
    public Long creatorId;
    public Optional<PokemonDTO> pokemon1;
    public Optional<PokemonDTO> pokemon2;
    public Optional<PokemonDTO> pokemon3;
    public Optional<PokemonDTO> pokemon4;
    public Optional<PokemonDTO> pokemon5;
    public Optional<PokemonDTO> pokemon6;

    public TeamCreateDTO(){
        this.name = "";
        this.creatorId = (long) 0;
    }
}
