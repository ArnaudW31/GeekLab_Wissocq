package com.geeklab.api;

import com.geeklab.api.dto.*;
import com.geeklab.api.entity.*;
import com.geeklab.api.repository.TeamRepository;
import com.geeklab.api.repository.UserRepository;
import com.geeklab.api.service.TeamService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TeamService teamService;

    @Test
    void createTeam_shouldAttachPokemonsToTeam() {

        User creator = new User();
        creator.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(creator));

        when(teamRepository.save(any(Team.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TeamCreateDTO dto = new TeamCreateDTO(
                "Geek Team",
                1L,
                List.of(
                        new PokemonDTO(1, 1, "Bulbasaur", "overgrow",
                                null, null, null, null, null, false),
                        new PokemonDTO(2, 4, "Charmander", "blaze",
                                null, null, null, null, null, false)
                )
        );

        TeamDTO result = teamService.createTeam(dto);

        assertNotNull(result);
        assertEquals("Geek Team", result.name);
    }
}
