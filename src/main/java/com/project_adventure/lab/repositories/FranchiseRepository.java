package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Franchise;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    Optional<Franchise> findByName(String name);
}
