package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.PlayerPublicDTO;
import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.repositories.GameRepository;
import com.project_adventure.lab.repositories.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    private PlayerPublicDTO playerDTO;

    private Player player;

    private Game game;

    @BeforeEach
    public void setUp(){
        player = new Player();
        player.setUsername("PlayerUser");
        player.setPassword("1212");
        userService.saveUser(player, playerRepository);

        game= new Game();
        game.setName("Pokemon Blue");
        game.setDuration(200);
        game.setDescription("Paquete complementario de Pokémon Red, introdujo la mecánica de intercambio entre versiones");
        game.setFranchise(null);
        gameService.createGame(game);

    }
    @AfterEach
    public void tearDown(){
        player.setGames(new ArrayList<>());
        playerRepository.delete(player);
        gameRepository.delete(game);
        //franchiseRepository.delete(franchise);
    }

    @Test
    @DisplayName("Correctly updates player username")
    public void updatePlayerUsernameTest(){

        PlayerPublicDTO playerDTO = new PlayerPublicDTO();

        playerDTO.setUsername("NewName");

        var gamesList = new ArrayList<Game>();
        gamesList.add(game);
        playerDTO.setGames(gamesList);

        Player playerUpdated = playerService.updatePlayer(player.getId(), playerDTO);

        assertEquals("NewName", playerUpdated.getUsername());
    }

}
