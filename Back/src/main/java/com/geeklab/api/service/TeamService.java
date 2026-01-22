package com.geeklab.api.service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geeklab.api.dto.PokemonDTO;
import com.geeklab.api.dto.TeamCreateDTO;
import com.geeklab.api.dto.TeamDTO;
import com.geeklab.api.entity.Pokemon;
import com.geeklab.api.entity.PokemonSlot;
import com.geeklab.api.entity.Team;
import com.geeklab.api.entity.User;
import com.geeklab.api.repository.PokemonRepository;
import com.geeklab.api.repository.TeamRepository;
import com.geeklab.api.repository.UserRepository;
import com.geeklab.api.repository.VoteRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamService {
    
    private final VoteRepository voteRepository;
    private final PokemonRepository pokemonRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamService(VoteRepository voteRepository, PokemonRepository pokemonRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.pokemonRepository = pokemonRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    
    /**
     * Récupère une page d'équipes
     * @param pageNum le numéro de la page (0 = première page)
     * @param pageSize le nombre d'équipes par page
     * @return ArrayList<Team> contenant les équipes pour cette page
     */
    public ArrayList<TeamDTO> getTeams(int pageNum, int pageSize, Optional<String> creatorName, Optional<String> teamName) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Team> page = this.teamRepository.findAll(pageable);

        ArrayList<TeamDTO> teams = new ArrayList<TeamDTO>();

        for (Team team : page.getContent()) {
            TeamDTO newTeam = new TeamDTO();

            newTeam.name = team.getName();
            newTeam.creatorName = team.getCreator().getUsername();
            newTeam.voteCount = this.voteRepository.countVotesForTeam(team.getId());

            List<Pokemon> pokemons = team.getPokemons();

            newTeam.pokemons = new ArrayList<PokemonDTO>();
            for (Pokemon pkmn : pokemons) {
                newTeam.pokemons.add(new PokemonDTO(pkmn));
            }

            teams.add(newTeam);
        }
        return teams;
    }

    @Transactional
    public TeamDTO createTeam(TeamCreateDTO dto) {

        // 1️⃣ Récupérer le créateur
        User creator = userRepository.findById(dto.creatorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Créer la Team
        Team team = new Team();
        team.setName(dto.name);
        team.setCreator(creator);
        team.setCreateDate(Date.from(Instant.now()));

        // 3️⃣ Persister la Team pour obtenir l'ID
        Team savedTeam = teamRepository.save(team);

        // 4️⃣ Ajouter les Pokémon avec clé composite (PokemonSlot)
        for (int i = 0; i < dto.pokemons.size(); i++) {
            PokemonDTO poke = dto.pokemons.get(i);

            // teamId = savedTeam.getId(), slotNum = i + 1
            PokemonSlot slot = new PokemonSlot(savedTeam.getId(), i);

            Pokemon newPoke = new Pokemon(
                    slot,
                    savedTeam,       // entité attachée
                    poke.pokeId,
                    poke.name,
                    poke.abilityId,
                    poke.itemId,
                    poke.move1Id,
                    poke.move2Id,
                    poke.move3Id,
                    poke.move4Id,
                    poke.isShiny
            );

            // Ajout à la collection de la Team (cascade va gérer l'INSERT)
            savedTeam.getPokemons().add(newPoke);
        }

        // 5️⃣ Aucun save supplémentaire nécessaire ! 
        // Hibernate flush automatiquement à la fin de la transaction grâce à @Transactional

        return new TeamDTO(savedTeam);
    }
}
