package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.ResourceNotFoundException;
import com.example.hrconnectpro.entities.Formation;
import com.example.hrconnectpro.repository.FormationRepository;
import com.example.hrconnectpro.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {
    private final FormationRepository formationRepository;
    @Override
    public Formation saveFormation(Formation formation) {
        if (formationRepository.findByTitre(formation.getTitre()) != null) {
            throw new ResourceNotFoundException("Formation name " + formation.getTitre() + " already exists");
        }
        return formationRepository.save(formation);
    }

    @Override
    public Formation findFormationById(Long id) {
        return formationRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }
}
