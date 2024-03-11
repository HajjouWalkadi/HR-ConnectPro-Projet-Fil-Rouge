package com.example.hrconnectpro.dto;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.enums.TypeConge;

import java.time.LocalDate;

public record CongeDTO(
        String name,
        String description,
        LocalDate dateDebut,
        LocalDate dateFin,
        TypeConge typeConge,
        Long employeeId) {


    public Conge toConge() {
        return Conge.builder()
                .name(name)
                .description(description)
                .typeConge(typeConge)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .employee(
                        Employee.builder()
                                // Check if employeeId is not null before parsing
                                .id(employeeId())
                                .build()
                )
                .build();
    }
}
