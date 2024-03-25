package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.repository.CongeRepository;
import com.example.hrconnectpro.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import static org.mockito.Mockito.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CongeServiceImplTest {

    @Mock
    private CongeRepository congeRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private CongeServiceImpl congeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // Mock the behavior of employeeService.getEmployeeById() if needed
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1L);
        when(employeeService.getEmployeeById(anyLong())).thenReturn(mockEmployee);
    }

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

        when(congeRepository.save(any(Conge.class))).thenReturn(conge); // Mock the save operation

        // Act
        Conge savedConge = congeService.saveConge(conge);

        // Assert
        assertNotNull(savedConge);
        assertEquals(conge.getName(), savedConge.getName());
        assertEquals(conge.getDateDebut(), savedConge.getDateDebut());
        assertEquals(conge.getDateFin(), savedConge.getDateFin());
        assertEquals(conge.getEmployee(), savedConge.getEmployee());
    }

    @Test
    public void test_invalid_conge_object() {
        // Arrange
        Conge conge = new Conge();
        conge.setName("Test Conge");
        conge.setDateDebut(LocalDate.now());
        conge.setDateFin(LocalDate.now().plusDays(5));
        Employee employee = new Employee();
        employee.setId(1L);
        conge.setEmployee(employee);

        when(congeRepository.save(any(Conge.class))).thenReturn(null); // Mock the save operation

        // Act
        Conge savedConge = congeService.saveConge(conge);

        // Assert
        assertNull(savedConge);
    }




}
