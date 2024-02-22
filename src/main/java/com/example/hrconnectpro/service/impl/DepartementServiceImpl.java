package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.ResourceNotFoundException;
import com.example.hrconnectpro.entities.Departement;
import com.example.hrconnectpro.repository.DepartementRepository;
import com.example.hrconnectpro.service.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartementServiceImpl implements DepartementService {
    private final DepartementRepository departementRepository;
    @Override
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id).orElseThrow();
    }

    @Override
    public Departement addDepartement(Departement departement) {
        if (departementRepository.findByName(departement.getNom()) != null) {
            throw new ResourceNotFoundException("Departement name " + departement.getNom() + " already exists");
        }
        return departementRepository.save(departement);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }
}
