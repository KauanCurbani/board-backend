package com.curbanii.board.application.Config;

import com.curbanii.board.core.Role.RoleUseCase;
import com.curbanii.board.core.User.internal.User;
import com.curbanii.board.core.User.UserUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private UserUseCase userUseCase;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleUseCase roleUseCase;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var adminRole = roleUseCase.getRoleByName("ADMIN");
        try {
            userUseCase.getUserByUsername("admin");
            System.out.println("Admin user already exists");
        } catch (Exception e) {
            System.out.println("Creating admin user");
            userUseCase.createUser(
                    User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(Set.of(adminRole))
                            .build()
            );
        }

    }
}
