package com.project_adventure.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{

    public FranchiseNotFoundException(Long id){
        super("Franchise not found with id: " + id);
    }

    public FranchiseNotFoundException(String name){
        super("Franchise not found with name: " + name);
    }

}
