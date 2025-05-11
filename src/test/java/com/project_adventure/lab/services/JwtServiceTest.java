package com.project_adventure.lab.services;

import com.project_adventure.lab.models.ERole;
import com.project_adventure.lab.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Test
    @DisplayName("Generate a token correctly")
    void generateToken(){
       String token = jwtService.generateToken("Luisa", "ROLE_EDITOR");

        System.out.println("Token: " + token);
    }
}
