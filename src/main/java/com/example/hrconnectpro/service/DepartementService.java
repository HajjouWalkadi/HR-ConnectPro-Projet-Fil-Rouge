package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Departement;

import java.util.List;

public interface DepartementService {
    Departement getDepartementById(Long id);
    Departement addDepartement(Departement departement);
    List<Departement> getAllDepartements();
}
