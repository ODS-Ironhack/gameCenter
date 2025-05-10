package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(String name);
}
