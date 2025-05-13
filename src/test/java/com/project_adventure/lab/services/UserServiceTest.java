package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.models.User;
import com.project_adventure.lab.repositories.AdminRepository;
import com.project_adventure.lab.repositories.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private PlayerRepository playerRepository;

    private Player player;
    private Player player1;
    private Admin admin;

    @BeforeEach
    public void setUp(){
        player = new Player();
        player.setUsername("PlayerUser");
        player.setPassword("7777");
        userService.saveUser(player, playerRepository);

        admin = new Admin();
        admin.setUsername("AdminUser");
        admin.setPassword("8888");
        admin.setName("Admin");
        admin.setSurname("User");
        userService.saveUser(admin, adminRepository);
    }

    @AfterEach
    public void tearDown(){
        playerRepository.delete(player);
        adminRepository.delete(admin);
    }


    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryptionTest(){
        assertTrue(player.getPassword().startsWith("$2a$"));
        System.out.println("Created player: " + player);
        assertTrue(admin.getPassword().startsWith("$2a$"));
        System.out.println("Created admin: " + admin);
    }

    @Test
    @DisplayName("Password checking is correct")
    public void checkPasswordTest(){
        String rawPassword= "7777";
        boolean isPassCorrect = userService.checkPassword(player, rawPassword);

        assertTrue(isPassCorrect);
    }

    @Test
    @DisplayName("Correctly finds player by username")
    public void findUserByUsernameTest(){
        Optional<User> resultPlayer = userService.findByUsername("PlayerUser");
        assertTrue(resultPlayer.isPresent());

        Optional<User> resultAdmin = userService.findByUsername("AdminUser");
        assertTrue(resultAdmin.isPresent());
    }

}
