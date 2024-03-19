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
        String designationNom,
        String departmentNom,
        String role
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
                                .nom(designationNom)
                                .build()
                )
                .departement(
                        Departement.builder()
                                .nom(departmentNom)
                                .build()
                )
                .role(Role.builder().build())
                .build();

    }

    public static EmployeeDTO toDto(Employee employee) {
        String departmentNom = employee.getDepartement() == null ? null : employee.getDepartement().getNom();
        String role = employee.getRole() == null ? null : employee.getRole().getName();
        String post = employee.getPoste() == null ? null : employee.getPoste().getNom();
        return new EmployeeDTO(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getEmail(),
                employee.getTelephone(),
                employee.getDateNaissance(),
                employee.getDateEmbauche(),
                employee.getDateDepart(),
                post,
                departmentNom,
                role
        );
    }
}
