package com.example.hrconnectpro.service;

import com.example.hrconnectpro.entities.Poste;

import java.util.List;
import java.util.Optional;

public interface PosteService {
    Poste getPosteById(Long id);
    Poste addPoste(Poste poste);
    List<Poste> getAllPostes();

    Optional<Poste> findByNom(String nom);
    void deletePoste(Long id);
}
