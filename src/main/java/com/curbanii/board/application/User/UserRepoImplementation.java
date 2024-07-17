package com.curbanii.board.application.User;

import com.curbanii.board.core.User.internal.User;
import com.curbanii.board.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepoImplementation implements UserRepository {
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepositoryJpa.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepositoryJpa.getUserByUsername(username);
    }
}
