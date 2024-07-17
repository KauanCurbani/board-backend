package com.curbanii.board.application.Role;

import com.curbanii.board.core.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepositoryJpa extends JpaRepository<Role, UUID>{
    Role getRoleByName(String name);
}
