package com.example.hrconnectpro.dto;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.entities.Role;
import com.example.hrconnectpro.enums.TypeConge;

import java.time.LocalDate;

public record CongeDTO(
        Long id,
        LocalDate dateDebut,
        LocalDate dateFin,
        String employeeId,
        String employeeName,
        String employeeEmail,
        String employeeRole,
        TypeConge typeConge) {

    public Conge toConge() {
        return Conge.builder()
                .id(id)
                .typeConge(typeConge)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .employee(
                        Employee.builder()
                                // Check if employeeId is not null before parsing
                                .id(employeeId != null ? Long.parseLong(employeeId) : null)
                                .lastName(employeeName)
                                .email(employeeEmail)
                                .role(
                                        Role.builder()
                                                .name(employeeRole)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}
