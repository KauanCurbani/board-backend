package com.curbanii.board.core.Team;

import com.curbanii.board.core.Team.internal.Team;

import java.util.List;
import java.util.UUID;

public interface TeamRepository {
    Team save(Team team);
    Team findById(UUID id);
    List<Team> findAll();
    void deleteById(UUID id);
    List<Team> findUserTeams(UUID userId);
}
