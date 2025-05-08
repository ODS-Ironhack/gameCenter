package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.ERole;
import com.project_adventure.lab.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findAllByName(ERole name);
}
