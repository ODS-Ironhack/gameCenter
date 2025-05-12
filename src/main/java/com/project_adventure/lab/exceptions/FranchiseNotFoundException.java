package com.project_adventure.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{

    public FranchiseNotFoundException(String message){
        super(message);
        System.out.println(message);
    }

}
