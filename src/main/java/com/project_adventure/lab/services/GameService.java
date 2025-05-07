package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.repositories.FranchiseRepository;
import com.project_adventure.lab.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(Game game) {
        Franchise franchise = game.getFranchise();

        if(franchise!=null){
           Optional<Franchise> existentFranchise = franchiseRepository.findByName(franchise.getName());
           if(existentFranchise.isPresent()){
           game.setFranchise(existentFranchise.get());}
           else {
               franchiseRepository.save(franchise);
           }
        }
        return gameRepository.save(game);
    }

    public Game getGameById(Long id) {
        //TODO crear excepcion personalizada
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no game by id: "+ id));
    }
}
