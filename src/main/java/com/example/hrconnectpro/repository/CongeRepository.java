package com.example.hrconnectpro.repository;

import com.example.hrconnectpro.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeRepository extends JpaRepository<Conge, Long> {
    Conge findCongeByEmployeeId(Long id);
    Conge findByName(String name);

}
