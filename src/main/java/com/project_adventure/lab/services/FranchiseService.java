package com.project_adventure.lab.services;

import com.project_adventure.lab.dtos.FranchisePatchDTO;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.repositories.FranchiseRepository;
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

    public Franchise getFranchiseByName(String name) {
        //TODO crear excepcion personalizada
        return franchiseRepository.findByName(name).orElseThrow(() -> new RuntimeException("There is no franchise by the name: "+ name));
    }

    public Franchise updateFranchise(Long id, Franchise newFranchise) {
        //TODO crear excepcion personalizada?
        Franchise existingFranchise = franchiseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no franchise by id: "+ id));
        newFranchise.setId(existingFranchise.getId());
        return franchiseRepository.save(newFranchise);
    }

    public Franchise patchFranchise(Long id, FranchisePatchDTO franchiseDTO) {
        //TODO crear excepcion personalizada
        Franchise existingFranchise = franchiseRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no franchise by id: "+ id));
        if(franchiseDTO.getName()!= null){
            existingFranchise.setName(franchiseDTO.getName());
        } else if(franchiseDTO.getDescription()!= null){
            existingFranchise.setDescription(franchiseDTO.getDescription());
        }
        return franchiseRepository.save(existingFranchise);
    }

}
