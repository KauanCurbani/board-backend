package com.curbanii.board.application.Team.Invite;

import com.curbanii.board.core.Team.Invite.internal.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InviteRepositoryJpa extends JpaRepository<Invite, UUID> {

    List<Invite> findByTeamId(UUID teamId);
}
