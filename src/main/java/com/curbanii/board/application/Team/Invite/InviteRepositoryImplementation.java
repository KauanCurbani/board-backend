package com.curbanii.board.application.Team.Invite;

import com.curbanii.board.core.Team.Invite.InviteRepository;
import com.curbanii.board.core.Team.Invite.internal.Invite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class InviteRepositoryImplementation implements InviteRepository {
    private InviteRepositoryJpa inviteRepositoryJpa;

    @Override
    public Invite save(Invite invite) {
        return inviteRepositoryJpa.save(invite);
    }

    @Override
    public Invite findById(UUID id) {
        return inviteRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<Invite> findByTeamId(UUID teamId) {
        return inviteRepositoryJpa.findByTeamId(teamId);
    }
}
