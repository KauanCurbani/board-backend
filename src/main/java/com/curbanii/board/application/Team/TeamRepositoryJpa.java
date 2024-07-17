package com.curbanii.board.application.Team;

import com.curbanii.board.core.Team.internal.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeamRepositoryJpa extends JpaRepository<Team, UUID> {

    List<Team> findByMembersId(UUID userId);
}
