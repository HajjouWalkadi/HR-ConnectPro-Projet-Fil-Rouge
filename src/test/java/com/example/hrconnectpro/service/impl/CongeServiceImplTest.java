package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CongeServiceImplTest {



            // When given a valid Conge object, it should save the Conge to the database and return the saved Conge object.
            @Test
            public void test_valid_conge_object() {
                // Arrange
                Conge conge = new Conge();
                conge.setName("Test Conge");
                conge.setDateDebut(LocalDate.now());
                conge.setDateFin(LocalDate.now().plusDays(5));
                Employee employee = new Employee();
                employee.setId(1L);
                conge.setEmployee(employee);

                // Act
                Conge savedConge = congeService.saveConge(conge);

                // Assert
                assertNotNull(savedConge);
                assertEquals(conge.getName(), savedConge.getName());
                assertEquals(conge.getDateDebut(), savedConge.getDateDebut());
                assertEquals(conge.getDateFin(), savedConge.getDateFin());
                assertEquals(conge.getEmployee(), savedConge.getEmployee());
            }

            // When given a Conge object with a null name, it should save the Conge to the database with a null name.
            @Test
            public void test_null_name_conge_object() {
                // Arrange
                Conge conge = new Conge();
                conge.setName(null);
                conge.setDateDebut(LocalDate.now());
                conge.setDateFin(LocalDate.now().plusDays(5));
                Employee employee = new Employee();
                employee.setId(1L);
                conge.setEmployee(employee);

                // Act
                Conge savedConge = congeService.saveConge(conge);

                // Assert
                assertNotNull(savedConge);
                assertNull(savedConge.getName());
                assertEquals(conge.getDateDebut(), savedConge.getDateDebut());
                assertEquals(conge.getDateFin(), savedConge.getDateFin());
                assertEquals(conge.getEmployee(), savedConge.getEmployee());
            }

            // When given a Conge object with a null dateDebut, it should save the Conge to the database with a null dateDebut.
            @Test
            public void test_null_dateDebut_conge_object() {
                // Arrange
                Conge conge = new Conge();
                conge.setName("Test Conge");
                conge.setDateDebut(null);
                conge.setDateFin(LocalDate.now().plusDays(5));
                Employee employee = new Employee();
                employee.setId(1L);
                conge.setEmployee(employee);

                // Act
                Conge savedConge = congeService.saveConge(conge);

                // Assert
                assertNotNull(savedConge);
                assertEquals(conge.getName(), savedConge.getName());
                assertNull(savedConge.getDateDebut());
                assertEquals(conge.getDateFin(), savedConge.getDateFin());
                assertEquals(conge.getEmployee(), savedConge.getEmployee());
            }

}