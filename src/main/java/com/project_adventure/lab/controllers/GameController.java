package com.project_adventure.lab.controllers;

import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

}
