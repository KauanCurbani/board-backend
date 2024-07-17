package com.curbanii.board.application.User;

import com.curbanii.board.core.User.internal.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {

    Optional<User> getUserByUsername(String username);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findById(UUID id);
}
