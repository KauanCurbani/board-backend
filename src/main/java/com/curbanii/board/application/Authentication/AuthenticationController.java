package com.curbanii.board.application.Authentication;

import com.curbanii.board.core.User.UserDto;
import com.curbanii.board.core.User.UserUseCase;
import com.curbanii.board.core.User.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final JwtEncoder jwtEncoder;
    private UserUseCase userUseCase;
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDto user = userUseCase.getUserByUsername(loginRequest.username());

        if (!userUseCase.isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("User not found");
        }

        var now = Instant.now();
        var expiresIn = 3000L;

        var claims = JwtClaimsSet.builder()
                .issuer("https://api.curbanii.net")
                .subject(user.getId().toString())
                .claims(c -> {
                            c.put("id", user.getId());
                            c.put("user", user);
                        }
                )
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest loginRequest) {
        var user = userUseCase
                .createUser(User.builder()
                        .username(loginRequest.username())
                        .password(passwordEncoder.encode(loginRequest.password())
                        )
                        .build()
                );

        var now = Instant.now();
        var expiresIn = 3000L;

        var claims = JwtClaimsSet.builder()
                .issuer("https://api.curbanii.net")
                .subject(user.getId().toString())
                .claims(c -> c.put("id", user.getId()))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}

