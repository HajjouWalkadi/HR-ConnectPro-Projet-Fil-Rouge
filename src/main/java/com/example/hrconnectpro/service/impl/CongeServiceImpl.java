package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.ResourceNotFoundException;
import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.repository.CongeRepository;
import com.example.hrconnectpro.service.CongeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CongeServiceImpl implements CongeService {

private final CongeRepository congeRepository;

    @Override
    public Conge saveConge(Conge conge) {
        if (congeRepository.findByName(conge.getName()) != null) {
            throw new ResourceNotFoundException("Conge name " + conge.getName() + " already exists");
        }
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

    @Override
    public void deleteConge(Long id) {
        findCongeById(id);
        congeRepository.deleteById(id);

    }
}
