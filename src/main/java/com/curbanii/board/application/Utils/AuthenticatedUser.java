package com.curbanii.board.application.Utils;

import com.curbanii.board.core.User.UserDto;
import com.curbanii.board.core.User.UserRepository;
import com.curbanii.board.core.User.UserUseCase;
import com.curbanii.board.core.User.internal.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticatedUser {
    private UUID id;
    private UserDto user;

    AuthenticatedUser() {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();

        Jwt jwt = (Jwt) authenticationToken.getCredentials();

        Map<String, Object> claims = jwt.getClaims();

        UUID id = UUID.fromString((String) claims.get("id"));
        Object user = claims.get("user");

        System.out.println(user);

        UserDto userDto = UserDto.builder()
                .id(id)
                .build();

        this.id = id;
        this.user = userDto;
    }
}
