package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.User;
import com.project_adventure.lab.repositories.AdminRepository;
import com.project_adventure.lab.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new User();
        admin.setUsername("Mary");
        admin.setPassword("1212");
        System.out.println("Initial user: " + admin);
        System.out.println(admin.getPassword());
        adminService.createAdmin(admin);
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(admin);
    }

    @Test
    @DisplayName("Password encryption is correct")
    public void passwordEncryption() {
        assertTrue(admin.getPassword().startsWith("$2a$")); // BCrypt hashes start with $2a$
        System.out.println("Created user: " + admin);
    }

    @Test
    @DisplayName("Password is correct")
    public void passwordIsCorrect() {
        boolean isCorrect = userService.checkPassword(admin, "1234");
        assertTrue(isCorrect);
        System.out.println("Is the password correct? " + isCorrect);
    }
}
