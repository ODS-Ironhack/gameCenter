package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.FranchisePatchDTO;
import com.project_adventure.lab.dtos.GamePatchDTO;
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

    //TODO dudas con este método.
    public Game updateGame(Long id, Game newGame) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no game by id: " + id));

        newGame.setId(id);

        Franchise incomingFranchise = newGame.getFranchise();
        Franchise existingFranchise = existingGame.getFranchise();

        if (incomingFranchise == null) {
            newGame.setFranchise(null);
        } else if (incomingFranchise.getId() != null) {
            newGame.setFranchise(incomingFranchise);
        } else if (existingFranchise != null && existingFranchise.getId() != null) {
            incomingFranchise.setId(existingFranchise.getId());
            newGame.setFranchise(incomingFranchise);
        } else {
            throw new RuntimeException("No previous franchise ID nor current franchise ID to assign to the incoming franchise");
        }

        return gameRepository.save(newGame);
    }

    public Game patchGame(Long id, GamePatchDTO gameDTO) {
        //TODO crear excepcion personalizada
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no game by id: "+ id));
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

}
