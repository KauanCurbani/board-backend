package com.curbanii.board.core.Team.Invite;

import com.curbanii.board.core.Team.Invite.internal.Invite;
import com.curbanii.board.core.Team.Invite.internal.InviteStatus;
import com.curbanii.board.core.Team.internal.Team;
import com.curbanii.board.core.User.UserDto;
import com.curbanii.board.core.User.internal.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteDto {
    private UUID id;
    private UUID teamId;
    private boolean available;
    private InviteStatus status;
    private UUID generatedBy;
    private UUID acceptedBy;

    public static InviteDto fromEntity(Invite invite) {
        return InviteDto.builder()
                .id(invite.getId())
                .teamId(invite.getTeamId())
                .available(invite.isAvailable())
                .status(invite.getStatus())
                .generatedBy(invite.getGeneratedBy())
                .acceptedBy(invite.getAcceptedBy())
                .build();
    }
}
