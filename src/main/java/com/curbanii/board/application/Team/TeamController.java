package com.curbanii.board.application.Team;

import com.curbanii.board.core.Team.Dto.CreateTeamDto;
import com.curbanii.board.core.Team.TeamUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@AllArgsConstructor
public class TeamController {
    TeamUseCase teamUseCase;

    @GetMapping("/all")
    public Object getTeams() {
        return teamUseCase.getTeams();
    }

    @GetMapping
    public Object getUserTeams() {
        return teamUseCase.findUserTeams();
    }

    @PostMapping
    public Object createTeam(@RequestBody CreateTeamDto createTeamDto) {
        return teamUseCase.createTeam(createTeamDto);
    }

}
