package com.curbanii.board.application.Team;

import com.curbanii.board.core.Team.internal.Team;
import com.curbanii.board.core.Team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TeamRepositoryImplementation implements TeamRepository {
    TeamRepositoryJpa teamRepositoryJpa;

    @Override
    public Team save(Team team) {
        return teamRepositoryJpa.save(team);
    }

    @Override
    public Team findById(UUID id) {
        return teamRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<Team> findAll() {
        return teamRepositoryJpa.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        teamRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Team> findUserTeams(UUID userId) {
        return teamRepositoryJpa.findByMembersId(userId);
    }
}
