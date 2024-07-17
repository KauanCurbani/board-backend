package com.curbanii.board.core.Team;

import com.curbanii.board.application.Utils.AuthenticatedUser;
import com.curbanii.board.core.Team.Dto.CreateTeamDto;
import com.curbanii.board.core.Team.internal.Team;
import com.curbanii.board.core.User.UserDto;
import com.curbanii.board.core.User.UserRepository;
import com.curbanii.board.core.User.UserUseCase;
import com.curbanii.board.core.User.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamUseCase {
    TeamRepository teamRepository;
    AuthenticatedUser authenticatedUser;
    UserUseCase userUseCase;
    UserRepository userRepository;

    public List<TeamDto> getTeams() {
        return teamRepository.findAll().stream().map(TeamDto::fromEntity).toList();
    }

    public List<TeamDto> findUserTeams() {
        UserDto userDto = userUseCase.getUserById(authenticatedUser.getId());
        return teamRepository.findUserTeams(userDto.getId()).stream().map(TeamDto::fromEntity).toList();
    }

    public TeamDto createTeam(CreateTeamDto createTeamDto) {
        UserDto userDto = userUseCase.getUserById(authenticatedUser.getId());

        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();

        Team team = Team.builder()
                .name(createTeamDto.getName())
                .members(List.of(user))
                .build();
        team = teamRepository.save(team);
        team = teamRepository.findById(team.getId());

        return TeamDto.fromEntity(team);
    }

    public TeamDto addMember(UUID teamId, UUID userId) {
        Team team = teamRepository.findById(teamId);
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        if (team.getMembers().stream().anyMatch(member -> member.getId().equals(user.getId()))) {
            throw new RuntimeException("User is already a member of the team");
        }

        team.getMembers().add(user);
        team = teamRepository.save(team);

        return TeamDto.fromEntity(team);
    }

}
