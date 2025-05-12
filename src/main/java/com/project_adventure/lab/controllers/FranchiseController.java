package com.project_adventure.lab.controllers;

import com.project_adventure.lab.dtos.FranchisePatchDTO;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.services.FranchiseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Franchise> getAllFranchises(@RequestParam(required = false) String name){
        if (name != null){
            return List.of(franchiseService.getFranchiseByName(name));
        } else {
            return franchiseService.getAllFranchises();
        }
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franchise getFranchiseById(@PathVariable Long id){
        return franchiseService.getFranchiseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Franchise createFranchise(@Valid @RequestBody Franchise franchise){
       return franchiseService.createFranchise(franchise);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franchise updateFranchise(@PathVariable Long id, @RequestBody @Valid Franchise franchise){
        return franchiseService.updateFranchise(id, franchise);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franchise patchFranchise(@PathVariable Long id, @RequestBody FranchisePatchDTO franchise){
        return franchiseService.patchFranchise(id, franchise);
    }



}
