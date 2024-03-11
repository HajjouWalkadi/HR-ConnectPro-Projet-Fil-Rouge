package com.example.hrconnectpro.repository;

import com.example.hrconnectpro.entities.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosteRepository extends JpaRepository<Poste, Long> {
    //Poste findPosteByEmployeeId(Long id);
    Poste findByNom(String name);

}
