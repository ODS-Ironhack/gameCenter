package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.GamePatchDTO;
import com.project_adventure.lab.exceptions.franchiseExceptions.FranchiseDescriptionMissingException;
import com.project_adventure.lab.exceptions.franchiseExceptions.FranchiseInfoMissingException;
import com.project_adventure.lab.exceptions.GameNotFoundException;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.models.Game;
import com.project_adventure.lab.repositories.FranchiseRepository;
import com.project_adventure.lab.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        if (franchise != null) {
            if (franchise.getId() != null) {
                Optional<Franchise> existentFranchise = franchiseRepository.findById(franchise.getId());
                if (existentFranchise.isPresent()) {
                    game.setFranchise(existentFranchise.get());
                } else {
                    throw new GameNotFoundException(franchise.getId());
                }
            }
            else if (franchise.getName() != null) {
                Optional<Franchise> existentFranchise = franchiseRepository.findByName(franchise.getName());
                if (existentFranchise.isPresent()) {
                    game.setFranchise(existentFranchise.get());
                } else {
                    if (franchise.getDescription() == null) {
                        throw new FranchiseDescriptionMissingException();
                    }
                    franchiseRepository.save(franchise);
                    game.setFranchise(franchise);
                }
            } else {
                throw new FranchiseInfoMissingException();
            }
        }

        return gameRepository.save(game);
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game updateGame(Long id, Game newGame) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

            newGame.setId(id);
            newGame.setFranchise(existingGame.getFranchise());

            return gameRepository.save(newGame);
    }

    public Game patchGame(Long id, GamePatchDTO gameDTO) {

        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        if(gameDTO.getName()!= null){
            existingGame.setName(gameDTO.getName());
        } else if(gameDTO.getDescription()!= null){
            existingGame.setDescription(gameDTO.getDescription());
        } else if(gameDTO.getDuration()!= 0){
        existingGame.setDuration(gameDTO.getDuration());
        } else if(gameDTO.getFranchise()!= null){
        existingGame.setFranchise(gameDTO.getFranchise());
        }
        return gameRepository.save(existingGame);
    }

    public ResponseEntity<String> deleteGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        gameRepository.delete(game);
        return ResponseEntity.ok("Game deleted: " + game.getName());
    }

}
