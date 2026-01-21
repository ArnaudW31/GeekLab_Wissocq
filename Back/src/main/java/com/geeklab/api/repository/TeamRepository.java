package com.geeklab.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geeklab.api.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>   {
    Optional<Team> findById(Long id);
    Optional<Team> findByName(String name);
    List<Team> findByCreatorId(Long creatorId);
}
