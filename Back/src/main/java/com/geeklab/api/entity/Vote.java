package com.geeklab.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @EmbeddedId
    private VoteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // correspond au champ userId dans VoteId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId") // correspond au champ teamId dans VoteId
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    // Tu peux ajouter d’autres colonnes, par ex. timestamp du vote
    @Column(nullable = false)
    private java.sql.Timestamp voteDate;

    public Vote() {}

    public Vote(VoteId id, User user, Team team, java.sql.Timestamp voteDate) {
        this.id = id;
        this.user = user;
        this.team = team;
        this.voteDate = voteDate;
    }

    // Getters / setters
    public VoteId getId() { return id; }
    public void setId(VoteId id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public java.sql.Timestamp getVoteDate() { return voteDate; }
    public void setVoteDate(java.sql.Timestamp voteDate) { this.voteDate = voteDate; }

    // toString
    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", teamId=" + (team != null ? team.getId() : null) +
                ", voteDate=" + voteDate +
                '}';
    }
}
