package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.repositories.FranchiseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public List<Franchise> getAllFranchises(){
        return franchiseRepository.findAll();
    }

    public Franchise createFranchise( Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise getFranchiseById(Long id) {
        //TODO crear excepcion personalizada
        return franchiseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no franchise by id: "+ id));
    }

    public Franchise updateFranchise(Long id, @Valid Franchise newFranchise) {
        //TODO crear excepcion personalizada?
        Franchise existingFranchise = franchiseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no franchise by id: "+ id));
        System.out.println(existingFranchise);
        newFranchise.setId(existingFranchise.getId());
        System.out.println(newFranchise);
        return franchiseRepository.save(newFranchise);
    }
}
