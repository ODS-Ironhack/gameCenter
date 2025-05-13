package com.project_adventure.lab.exceptions.franchiseExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class FranchiseAlreadyExistsException extends RuntimeException {
    public FranchiseAlreadyExistsException(String name) {
        super("Franchise with name '" + name + "' already exists.");
    }
}
