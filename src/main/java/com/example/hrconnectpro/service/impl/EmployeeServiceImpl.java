package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.entities.Departement;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.entities.Poste;
import com.example.hrconnectpro.entities.Role;
import com.example.hrconnectpro.repository.EmployeeRepository;
import com.example.hrconnectpro.repository.RoleRepository;
import com.example.hrconnectpro.service.DepartementService;
import com.example.hrconnectpro.service.EmployeeService;
import com.example.hrconnectpro.service.PosteService;
import com.example.hrconnectpro.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PosteService posteService;
    private final DepartementService departementService;
    private final RoleService roleService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PosteService posteService, DepartementService departementService, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.posteService = posteService;
        this.departementService = departementService;
        this.roleService = roleService;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Poste poste = posteService.findByNom(employee.getPoste().getNom()).orElseThrow();
        Departement departement = departementService.findByNom(employee.getDepartement().getNom()).orElseThrow();
        Role roleUser = roleService.findByNom("employee")
                .orElseGet(() -> roleService.save(
                Role.builder().name("employee").build(), true));
        employee.setPoste(poste);
        employee.setDepartement(departement);
        employee.setRole(roleUser);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setEmail(employee.getEmail());
        employee.setTelephone(employee.getTelephone());
        employee.setPoste(employee.getPoste());

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
