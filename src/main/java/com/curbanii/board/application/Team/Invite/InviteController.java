package com.curbanii.board.application.Team.Invite;

import com.curbanii.board.core.Team.Invite.InviteDto;
import com.curbanii.board.core.Team.Invite.InviteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team/invite")
@AllArgsConstructor
public class InviteController {
    private InviteUseCase inviteUseCase;

    @PostMapping("/{teamId}")
    public InviteDto createInvite(@PathVariable UUID teamId) {
        return inviteUseCase.createInvite(teamId);
    }

    @PostMapping("/accept/{inviteId}")
    public InviteDto acceptInvite(@PathVariable UUID inviteId) {
        return inviteUseCase.acceptInvite(inviteId);
    }

    @GetMapping("/{teamId}")
    public List<InviteDto> getInvitesByTeamId(@PathVariable UUID teamId) {
        return inviteUseCase.getInvitesByTeamId(teamId);
    }

}
