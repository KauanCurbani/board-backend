package com.curbanii.board.core.Team;

import com.curbanii.board.core.Team.internal.Team;
import com.curbanii.board.core.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String id;
    private String name;
    private List<UserDto> members;

    static TeamDto fromEntity(Team team) {
        return TeamDto.builder()
                .id(team.getId().toString())
                .name(team.getName())
                .members(team.getMembers().stream().map(UserDto::fromEntity).toList())
                .build();
    }
}
