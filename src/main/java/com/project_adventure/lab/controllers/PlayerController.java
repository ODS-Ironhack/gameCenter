package com.project_adventure.lab.controllers;


import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.services.GameService;
import com.project_adventure.lab.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAllGames(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerByUsername(@PathVariable String username){

    }
}