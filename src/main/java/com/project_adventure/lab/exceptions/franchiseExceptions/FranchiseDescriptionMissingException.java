package com.project_adventure.lab.exceptions.franchiseExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FranchiseDescriptionMissingException extends RuntimeException{
    public FranchiseDescriptionMissingException() {
        super("Description must not be null when creating a new Franchise.");
    }
}
