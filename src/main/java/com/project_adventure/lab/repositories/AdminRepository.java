package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String name);
}
