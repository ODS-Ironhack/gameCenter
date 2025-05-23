package com.project_adventure.lab.exceptions;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id){
        super("Game not found with id: " + id);
    }

    public GameNotFoundException(String name){
        super("Game not found with name: " + name);
    }
}
