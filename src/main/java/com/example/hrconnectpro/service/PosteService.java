package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.entities.Poste;

import java.util.List;

public interface PosteService {
    Poste getPosteById(Long id);
    Poste addPoste(Poste poste);
    List<Poste> getAllPostes();
}
