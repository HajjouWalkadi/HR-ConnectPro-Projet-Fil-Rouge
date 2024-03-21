package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.ResourceNotFoundException;
import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.enums.StatusConge;
import com.example.hrconnectpro.repository.CongeRepository;
import com.example.hrconnectpro.service.CongeService;
import com.example.hrconnectpro.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CongeServiceImpl implements CongeService {

private final CongeRepository congeRepository;
private final EmployeeService employeeService;

    @Override
    public Conge saveConge(Conge conge) {

        Employee employee = employeeService.getEmployeeById(conge.getEmployee().getId());

        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found");
        }
        conge.setEmployee(employee);

        return congeRepository.save(conge);
    }

    @Override
    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    @Override
    public Conge updateConge(Long id, Conge conge) {
        Conge conge1 = findCongeById(id);
        conge1.setName(conge.getName());
        conge1.setDateDebut(conge.getDateDebut());
        conge1.setDateFin(conge.getDateFin());
        conge1.setEmployee(conge.getEmployee());
        return congeRepository.save(conge1);
    }


    @Override
    public Conge findCongeById(Long id) {
        return congeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conge id " + id + "not found"));
    }
    @Override
    public Conge findCongeByEmployeeId(Long id) {
        return congeRepository.findCongeByEmployeeId(id);
    }

    @Transactional
    @Override
    public void deleteConge(Long id) {
        findCongeById(id);
        congeRepository.deleteById(id);

    }

    @Override
    public Conge updateStatus(Long id, StatusConge status) {
        Conge conge = findCongeById(id);
        conge.setStatus(status);
        return congeRepository.save(conge);
    }
}
