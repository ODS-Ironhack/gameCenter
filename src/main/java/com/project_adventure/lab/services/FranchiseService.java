package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.FranchisePatchDTO;
import com.project_adventure.lab.exceptions.FranchiseAlreadyExistsException;
import com.project_adventure.lab.exceptions.FranchiseNotFoundException;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public List<Franchise> getAllFranchises(){
        return franchiseRepository.findAll();
    }

    public Franchise createFranchise( Franchise franchise) {

        Optional<Franchise> existing = franchiseRepository.findByName(franchise.getName());

        if (existing.isPresent()) {
            System.out.println("Esto ya existe");
            throw new FranchiseAlreadyExistsException(franchise.getName());

        }

        return franchiseRepository.save(franchise);
    }

    public Franchise getFranchiseById(Long id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    public Franchise getFranchiseByName(String name) {
        return franchiseRepository.findByName(name).orElseThrow(() -> new FranchiseNotFoundException(name));
    }

    public Franchise updateFranchise(Long id, Franchise newFranchise) {
        Franchise existingFranchise = franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
        newFranchise.setId(existingFranchise.getId());
        return franchiseRepository.save(newFranchise);
    }

    public Franchise patchFranchise(Long id, FranchisePatchDTO franchiseDTO) {
        Franchise existingFranchise = franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
        if(franchiseDTO.getName()!= null){
            existingFranchise.setName(franchiseDTO.getName());
        } else if(franchiseDTO.getDescription()!= null){
            existingFranchise.setDescription(franchiseDTO.getDescription());
        }
        return franchiseRepository.save(existingFranchise);
    }

}
