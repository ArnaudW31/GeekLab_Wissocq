package com.geeklab.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geeklab.api.dto.TeamCreateDTO;
import com.geeklab.api.dto.TeamDTO;
import com.geeklab.api.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    //Prévu de mettre des filtres mais c'est en WIP
    @GetMapping
    public List<TeamDTO> getTeams(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return teamService.getTeams(page, size, Optional.empty(), Optional.empty());
    }

    @PostMapping
    public TeamDTO createTeam(@RequestBody TeamCreateDTO dto) {
        return teamService.createTeam(dto);
    }

}
