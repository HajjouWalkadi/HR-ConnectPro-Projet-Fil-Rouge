package com.example.hrconnectpro.repository;

import com.example.hrconnectpro.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    Formation findByTitre(String name);
}
