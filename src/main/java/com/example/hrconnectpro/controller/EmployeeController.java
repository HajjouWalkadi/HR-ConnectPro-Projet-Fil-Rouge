package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.dto.EmployeeDTO;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployee();
        if(employees.isEmpty()) {
            return ResponseMessage.notFound("Employee not found");
        }else {
            return ResponseMessage.ok(
                    employees.stream().map(EmployeeDTO::toDto).toList(),
                    "Success");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(Long id) {
        return ResponseMessage.ok(employeeService.getEmployeeById(id), "Success");
    }

    @PostMapping("/save")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        Employee employee1 = employeeService.addEmployee(employee.toEmployee());
        if(employee1 == null) {
            return ResponseMessage.badRequest("Employee not created");
        }else {
            return ResponseMessage.created(employee1,"Employee created successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        Employee employee1 = employeeService.updateEmployee(employee, id);
        if(employee1 == null) {
            return ResponseMessage.badRequest("Employee not updated");
        }else {
            return ResponseMessage.created(employee1, "Employee updated successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null) {
            return ResponseMessage.notFound("Employee not found");
        }else {
            employeeService.deleteEmployee(id);
            return ResponseMessage.ok("Employee deleted successfully", null);
        }
    }

}
