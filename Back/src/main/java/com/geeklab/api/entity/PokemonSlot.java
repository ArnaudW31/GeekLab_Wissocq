package com.geeklab.api.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PokemonSlot implements Serializable {

    private Long teamId;   // correspond à Team.id
    private Integer slotNum;

    public PokemonSlot() {}

    public PokemonSlot(Long teamId, Integer slotNum) {
        this.teamId = teamId;
        this.slotNum = slotNum;
    }

    // getters / setters
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public Integer getSlotNum() { return slotNum; }
    public void setSlotNum(Integer slotNum) { this.slotNum = slotNum; }

    // equals & hashCode obligatoires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonSlot)) return false;
        PokemonSlot slotId = (PokemonSlot) o;
        return Objects.equals(teamId, slotId.teamId) &&
               Objects.equals(slotNum, slotId.slotNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, slotNum);
    }
}