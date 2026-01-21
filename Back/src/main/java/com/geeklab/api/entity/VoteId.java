package com.geeklab.api.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    private Long userId;
    private Long teamId;

    public VoteId() {}

    public VoteId(Long userId, Long teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

}