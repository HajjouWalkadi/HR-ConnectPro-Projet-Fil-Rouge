package com.example.hrconnectpro.dto.response;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.enums.StatusConge;
import com.example.hrconnectpro.enums.TypeConge;

import java.time.LocalDate;

public record CongeResponseDto (
    Long id,
    TypeConge typeConge,
    LocalDate dateDebut,
    LocalDate dateFin,
    StatusConge status,
    String description,
    Long employeeId,
    String employeeFullName

){
    public Conge toEntity() {
        return Conge.builder()
                .id(id)
                .typeConge(typeConge)
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .status(status)
                .description(description)
                .employee(Employee.builder().id(employeeId).build())
                .build();
    }

    public static CongeResponseDto toDto(Conge conge) {
        return new CongeResponseDto(
                conge.getId(),
                conge.getTypeConge(),
                conge.getDateDebut(),
                conge.getDateFin(),
                conge.getStatus(),
                conge.getDescription(),
                conge.getEmployee().getId(),
                conge.getEmployee().getFirstName() + " " +  conge.getEmployee().getLastName()
        );
    }
}
