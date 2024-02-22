package com.example.hrconnectpro.repository;

import com.example.hrconnectpro.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Departement findByName(String name);

}
