package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.repositories.AdminRepository;
import com.project_adventure.lab.repositories.PlayerRepository;
import com.project_adventure.lab.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private PlayerRepository playerRepository;

    private Player player;

    @BeforeEach
    public void setUp(){
        player = new Player();
        player.setUsername("Gabi");
        player.setPassword("7777");
        System.out.println("Initial player: " + player);
        userService.saveUser(player, playerRepository);
    }

    @AfterEach
    public void tearDown(){
        playerRepository.delete(player);
    }

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryption(){
        assertTrue(player.getPassword().startsWith("$2a$"));
        System.out.println("Created player: " + player);
    }

    @Test
    @DisplayName("Password is correct")
    public void passwordIsCorrect(){

    }
}
