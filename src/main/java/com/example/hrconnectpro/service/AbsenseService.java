package com.example.hrconnectpro.service;


import com.example.hrconnectpro.entities.Absense;
import com.example.hrconnectpro.entities.Employee;

import java.util.List;

public interface AbsenseService {
    List<Absense> getAllAbsenses();

    List<Absense> getEmployeeAbsenses(Employee employee);

    void saveAbsense(Absense absense, Employee employee);

    Absense getAbsenseById(long id);

    void deleteAbsenseById(long id);
}
