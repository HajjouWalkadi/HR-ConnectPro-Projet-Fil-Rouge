package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.enums.StatusConge;

import java.util.List;

public interface CongeService {

    Conge saveConge(Conge conge);

    List<Conge> getAllConges();

    Conge updateConge(Long id, Conge conge);
    Conge findCongeById(Long id);

    Conge findCongeByEmployeeId(Long id);

    void deleteConge(Long id);


    Conge updateStatus(Long id, StatusConge status);
}
