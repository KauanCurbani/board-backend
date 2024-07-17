package com.curbanii.board.core.Team.Invite;

import com.curbanii.board.application.Utils.AuthenticatedUser;
import com.curbanii.board.core.Team.Invite.internal.Invite;
import com.curbanii.board.core.Team.Invite.internal.InviteStatus;
import com.curbanii.board.core.Team.TeamDto;
import com.curbanii.board.core.Team.TeamUseCase;
import com.curbanii.board.core.User.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InviteUseCase {
    private AuthenticatedUser authenticatedUser;
    private InviteRepository inviteRepository;
    private TeamUseCase teamUseCase;

    public InviteDto createInvite(UUID teamId) {
        List<TeamDto> userTeams = teamUseCase.findUserTeams();




        Invite invite = Invite.builder()
                .available(true)
                .generatedBy(authenticatedUser.getId())
                .teamId(teamId)
                .status(InviteStatus.PENDING)
                .build();
        invite = inviteRepository.save(invite);

        return InviteDto.fromEntity(invite);
    }

    public InviteDto acceptInvite(UUID inviteId) {
        Invite invite = inviteRepository.findById(inviteId);

        if (invite.getGeneratedBy().equals(authenticatedUser.getId())) {
            throw new RuntimeException("You can't accept your own invite");
        }

        invite.setStatus(InviteStatus.ACCEPTED);
        invite.setAvailable(false);
        invite.setAcceptedBy(authenticatedUser.getId());

        teamUseCase.addMember(invite.getTeamId(), authenticatedUser.getId());

        invite = inviteRepository.save(invite);
        return InviteDto.fromEntity(invite);
    }

    public List<InviteDto> getInvitesByTeamId(UUID teamId) {
        List<Invite> invites = inviteRepository.findByTeamId(teamId);
        return invites.stream().map(InviteDto::fromEntity).toList();
    }

}
