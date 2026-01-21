package com.geeklab.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.geeklab.api.entity.Pokemon;
import com.geeklab.api.entity.PokemonSlot;

public interface PokemonRepository extends JpaRepository<Pokemon, PokemonSlot>  {

    //Pokemons de l'équipe
    @Query("""
        SELECT p 
        FROM Pokemon p 
        WHERE p.team.id = :teamId
    """)
    List<Pokemon> getPokemonFromTeam(Long teamId);
}
