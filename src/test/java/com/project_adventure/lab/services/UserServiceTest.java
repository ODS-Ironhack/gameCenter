package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.repositories.AdminRepository;
import com.project_adventure.lab.repositories.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println("Initial player: " + player + player.getPassword());
        userService.saveUser(player, playerRepository);
    }

    @AfterEach
    public void tearDown(){
        playerRepository.delete(player);
    }


    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryptionTest(){
        assertTrue(player.getPassword().startsWith("$2a$"));
        System.out.println("Created player: " + player + " " + player.getPassword());
    }

    @Test
    @DisplayName("Password is correct")
    public void checkPasswordTest(){
        String rawPassword= "7777";
        boolean isPassCorrect = userService.checkPassword(player, rawPassword);

        assertTrue(isPassCorrect);
    }
}
