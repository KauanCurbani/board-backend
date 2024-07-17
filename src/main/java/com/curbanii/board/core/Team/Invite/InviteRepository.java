package com.curbanii.board.core.Team.Invite;

import com.curbanii.board.core.Team.Invite.internal.Invite;

import java.util.List;
import java.util.UUID;

public interface InviteRepository {
    Invite save(Invite invite);
    Invite findById(UUID id);
    List<Invite> findByTeamId(UUID teamId);
}
