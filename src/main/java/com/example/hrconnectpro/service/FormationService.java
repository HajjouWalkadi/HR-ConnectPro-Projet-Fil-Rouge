package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Formation;

import java.util.List;

public interface FormationService {
    Formation saveFormation(Formation formation);
    Formation findFormationById(Long id);
    void deleteFormation(Long id);
    List<Formation> getAllFormations();
}
