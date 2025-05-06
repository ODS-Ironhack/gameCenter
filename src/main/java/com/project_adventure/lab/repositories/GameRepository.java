package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
