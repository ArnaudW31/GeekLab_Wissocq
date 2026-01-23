package com.geeklab.api.dto;

public class VoteDTO {
    public Long voterId;
    public Long teamId;

    public VoteDTO(Long voterId, Long teamId){
        this.voterId = voterId;
        this.teamId = teamId;
    }
}
