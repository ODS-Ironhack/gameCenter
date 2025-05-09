package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
       //player.setRole(ERole.PLAYER);
        return player;
    }
}
