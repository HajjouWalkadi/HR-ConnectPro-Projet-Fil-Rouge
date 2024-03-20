package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.config.handlers.exception.ResourceNotFoundException;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.entities.Poste;
import com.example.hrconnectpro.repository.PosteRepository;
import com.example.hrconnectpro.service.PosteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosteServiceImpl implements PosteService {
    private final PosteRepository posteRepository;
    @Override
    public Poste getPosteById(Long id) {
      return posteRepository.findById(id).orElseThrow();
    }

    @Override
    public Poste addPoste(Poste poste) {
        Poste byNom = posteRepository.findByNom(poste.getNom()).orElse(null);
        if (byNom != null) {
            throw new ResourceNotFoundException("Poste name " + poste.getNom() + " already exists");
        }
        return posteRepository.save(poste);
    }

    @Override
    public List<Poste> getAllPostes() {
        return posteRepository.findAll();
    }

    @Override
    public Optional<Poste> findByNom(String nom) {
        return posteRepository.findByNom(nom);
    }
}
