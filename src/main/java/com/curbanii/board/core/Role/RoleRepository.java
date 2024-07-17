package com.curbanii.board.core.Role;

import java.util.UUID;

public interface RoleRepository {
    Role save(Role role);
    Role findById(UUID id);
    Role getRoleByName(String name);
}
