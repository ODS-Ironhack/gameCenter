package com.project_adventure.lab.controllers;

import com.project_adventure.lab.dtos.FranchisePatchDTO;
import com.project_adventure.lab.dtos.GamePatchDTO;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.services.GameService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@Valid @RequestBody Game game){
        return gameService.createGame(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game updateGame(@PathVariable Long id, @RequestBody @Valid Game game){
        return gameService.updateGame(id, game);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game patchGame(@PathVariable Long id, @RequestBody GamePatchDTO game){
        return gameService.patchGame(id, game);
    }



}
