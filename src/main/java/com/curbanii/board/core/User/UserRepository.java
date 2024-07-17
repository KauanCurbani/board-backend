package com.curbanii.board.core.User;

import com.curbanii.board.core.User.internal.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> getUserByUsername(String username);
}
