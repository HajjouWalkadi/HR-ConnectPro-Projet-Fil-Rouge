package com.example.hrconnectpro.repository;

import com.example.hrconnectpro.entities.Absense;
import com.example.hrconnectpro.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenseRepository extends JpaRepository<Absense, Long> {

  List<Absense> findAllByEmployee(Employee employee);

}
