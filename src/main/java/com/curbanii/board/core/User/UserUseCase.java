package com.curbanii.board.core.User;

import com.curbanii.board.application.Authentication.LoginRequest;
import com.curbanii.board.core.User.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;

    public UserDto createUser(User user) {
        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.fromEntity(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository
                .getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.fromEntity(user);
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, BCryptPasswordEncoder passwordEncoder) {
        User user = userRepository
                .getUserByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(loginRequest.password(), user.getPassword());
    }
}
