package com.curbanii.board.core.Team.Invite.internal;

import com.curbanii.board.application.Utils.BaseEntity;
import com.curbanii.board.core.Team.internal.Team;
import com.curbanii.board.core.User.internal.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "invites")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "team_id")
    private UUID teamId;

    private boolean available;
    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    private UUID generatedBy;
    private UUID acceptedBy;
}
