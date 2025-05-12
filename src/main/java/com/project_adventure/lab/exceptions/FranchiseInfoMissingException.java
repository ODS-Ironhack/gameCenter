package com.project_adventure.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FranchiseInfoMissingException extends RuntimeException {
    public FranchiseInfoMissingException() {
        super("Franchise must have at least an ID or a name.");
    }
}
