package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.PlayerPublicDTO;
import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.models.Player;
import com.project_adventure.lab.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
       //player.setRole(ERole.PLAYER);
        return player;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public PlayerPublicDTO getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .map(player -> new PlayerPublicDTO(player.getUsername(), (List<Game>) player.getGames()))
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public Player getPlayerForAdmin(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }



    public Player updatePlayer(Long id, PlayerPublicDTO newPlayer){
        Player existingPlayer= playerRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no player by id: "+ id));
        existingPlayer.setGames(newPlayer.getGames());

        return playerRepository.save(existingPlayer);
    }
}
