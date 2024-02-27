package com.example.hrconnectpro.dto;

import com.example.hrconnectpro.entities.Departement;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.entities.Poste;
import com.example.hrconnectpro.entities.Role;

import java.util.Date;

public record EmployeeDTO(
        Long id,
        String lastName,
        String firstName,
        String email,
        String telephone,
        Date dateNaissance,
        Date dateEmbauche,
        Date dateDepart,
        String poste,
        int salaire,
        String departement,
        String role,
        Long formationId,
        Long congeId
) {
    public Employee toEmployee() {
        return Employee.builder()
                .id(id)
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .telephone(telephone)
                .dateNaissance(dateNaissance)
                .dateEmbauche(dateEmbauche)
                .dateDepart(dateDepart)
                .poste(
                        Poste.builder()
                                .nom(poste)
                                .build()
                )
                .salaire(salaire)
                .departement(
                        Departement.builder()
                                .nom(departement)
                                .build()
                )
                .role(Role.builder().build())
                .build();

    }
}
