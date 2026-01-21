package com.geeklab.api.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @EmbeddedId
    private PokemonSlot id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId") // correspond au champ teamId dans SlotId
    @JoinColumn(name = "teamId")
    private Team team;

    @Column(nullable = false)
    private int pokeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int abilityId;

    @Column
    private int itemId;

    @Column
    private int move1Id;
    
    @Column
    private int move2Id;
    
    @Column
    private int move3Id;
    
    @Column
    private int move4Id;
    
    @Column(nullable = false)
    private boolean isShiny;

    public Pokemon() {}

    public Pokemon(PokemonSlot id, Team team, int pokeId, String name, int abilityId,
                   int itemId, int move1Id, int move2Id, int move3Id, int move4Id, boolean isShiny) {
        this.id = id;
        this.team = team;
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

    public PokemonSlot getId() { return id; }
    public void setId(PokemonSlot id) { this.id = id; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public int getPokeId() { return pokeId; }
    public void setPokeId(int pokeId) { this.pokeId = pokeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAbilityId() { return abilityId; }
    public void setAbilityId(int abilityId) { this.abilityId = abilityId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getMove1Id() { return move1Id; }
    public void setMove1Id(int move1Id) { this.move1Id = move1Id; }

    public int getMove2Id() { return move2Id; }
    public void setMove2Id(int move2Id) { this.move2Id = move2Id; }

    public int getMove3Id() { return move3Id; }
    public void setMove3Id(int move3Id) { this.move3Id = move3Id; }

    public int getMove4Id() { return move4Id; }
    public void setMove4Id(int move4Id) { this.move4Id = move4Id; }

    public boolean isShiny() { return isShiny; }
    public void setShiny(boolean shiny) { isShiny = shiny; }

    // ✅ toString
    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", team=" + (team != null ? team.getId() : null) +
                ", pokeId=" + pokeId +
                ", name='" + name + '\'' +
                ", abilityId=" + abilityId +
                ", itemId=" + itemId +
                ", move1Id=" + move1Id +
                ", move2Id=" + move2Id +
                ", move3Id=" + move3Id +
                ", move4Id=" + move4Id +
                ", isShiny=" + isShiny +
                '}';
    }

    // ✅ equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokeId == pokemon.pokeId &&
               abilityId == pokemon.abilityId &&
               itemId == pokemon.itemId &&
               move1Id == pokemon.move1Id &&
               move2Id == pokemon.move2Id &&
               move3Id == pokemon.move3Id &&
               move4Id == pokemon.move4Id &&
               isShiny == pokemon.isShiny &&
               id.equals(pokemon.id) &&
               ((team != null && pokemon.team != null) ? team.getId().equals(pokemon.team.getId()) : team == pokemon.team) &&
               name.equals(pokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team != null ? team.getId() : null, pokeId, name,
                            abilityId, itemId, move1Id, move2Id, move3Id, move4Id, isShiny);
    }
}