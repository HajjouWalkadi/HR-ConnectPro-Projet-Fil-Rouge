package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);

    //List<Employee> getAllEmployeesPaginated(Pageable pageable);
}

