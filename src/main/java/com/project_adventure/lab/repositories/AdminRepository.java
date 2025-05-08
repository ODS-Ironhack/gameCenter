package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
