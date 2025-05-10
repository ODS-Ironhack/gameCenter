package com.project_adventure.lab.controllers;

import com.project_adventure.lab.dtos.LoginRequestUserDTO;
import com.project_adventure.lab.models.User;
import com.project_adventure.lab.services.JwtService;
import com.project_adventure.lab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestUserDTO user){
        System.out.println("Buscando usuario: " + user.getUsername() + " con contraseña: " + user.getPassword());
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        System.out.println("Buscando usuario: " + user.getUsername() + " con contraseña: " + user.getPassword()+ " encuentra:" + optionalUser);
        if(optionalUser.isPresent()){
            User foundUser = optionalUser.get();

            if(userService.checkPassword(foundUser,user.getPassword())){
                String token = jwtService.generateToken(foundUser.getUsername(),
                        foundUser.getRole().name());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login incorrect");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
