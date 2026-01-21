package com.geeklab.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geeklab.api.entity.Team;
import com.geeklab.api.entity.Vote;
import com.geeklab.api.entity.VoteId;

public interface VoteRepository extends JpaRepository<Vote, VoteId>  {

    // Nb votes par équipe
    @Query("""
        SELECT v.team.id, COUNT(v)
        FROM Vote v
        GROUP BY v.team.id
        ORDER BY COUNT(v)
    """)
    List<Object[]> countVotesByTeam();

    // Nb votes pour l'équipe
    @Query("""
        SELECT COUNT(v)
        FROM Vote v
        WHERE v.team.id = :teamId
    """)
    Long countVotesForTeam(@Param("teamId") Long teamId);


    // Lister les équipes pour lesquelles un utilisateur a voté
    @Query("""
        SELECT v.team
        FROM Vote v
        WHERE v.user.id = :userId
    """)
    List<Team> findTeamsVotedByUser(@Param("userId") Long userId);

}
