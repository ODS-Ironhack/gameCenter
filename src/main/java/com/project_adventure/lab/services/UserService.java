package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.models.User;
import com.project_adventure.lab.repositories.AdminRepository;
import com.project_adventure.lab.repositories.PlayerRepository;
import com.project_adventure.lab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AdminRepository adminRepository;

    // De esta manera puedo pasar como argumentos un player o admin y un repository de cualquiera de los dos para hacer save
    public <T extends User> T saveUser(T user, JpaRepository<T, Long> repository) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()){
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public Optional<User> findByUsername(String username) {
        Optional<Player> player = playerRepository.findByUsername(username);
        if (player.isPresent()) {
            return Optional.of(player.get()); // hay que devolver un optional de User, no vale devolver el opt de player
        }
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return Optional.of(admin.get());
        }
        return Optional.empty();
    }
}
