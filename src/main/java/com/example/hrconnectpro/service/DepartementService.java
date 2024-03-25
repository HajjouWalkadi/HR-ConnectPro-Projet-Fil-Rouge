package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Departement;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    Departement getDepartementById(Long id);
    Departement addDepartement(Departement departement);
    List<Departement> getAllDepartements();

    Optional<Departement> findByNom(String nom);
    void deleteDepartement(Long id);
    Long getDepartementsCount();
}
