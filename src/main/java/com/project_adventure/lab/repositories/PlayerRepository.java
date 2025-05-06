package com.project_adventure.lab.repositories;

import com.project_adventure.lab.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
