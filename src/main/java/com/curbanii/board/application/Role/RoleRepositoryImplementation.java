package com.curbanii.board.application.Role;

import com.curbanii.board.core.Role.Role;
import com.curbanii.board.core.Role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class RoleRepositoryImplementation  implements RoleRepository {
    RoleRepositoryJpa roleRepositoryJpa;

    @Override
    public Role save(Role role) {
        return roleRepositoryJpa.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepositoryJpa.getRoleByName(name);
    }
}
