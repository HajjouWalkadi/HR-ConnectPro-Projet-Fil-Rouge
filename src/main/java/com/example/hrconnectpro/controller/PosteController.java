package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.entities.Poste;
import com.example.hrconnectpro.service.PosteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/poste")
@RequiredArgsConstructor
public class PosteController {
    private final PosteService posteService;

    @GetMapping("/all")
    public ResponseEntity getAllPostes() {
        List<Poste> postes = posteService.getAllPostes();
        if(postes.isEmpty()) {
            return ResponseMessage.notFound("Poste not found");
        }else {
            return ResponseMessage.ok(postes, "Success");
        }
    }

    @PostMapping("/add")
    public ResponseEntity addPoste(Poste poste) {
        Poste poste1 = posteService.addPoste(poste);
        if(poste1 == null) {
            return ResponseMessage.badRequest("Poste not created");
        }else {
            return ResponseMessage.created(poste1, "Poste created successfully");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPosteById(Long id) {
        return ResponseMessage.ok( posteService.getPosteById(id), "Success");
    }
}
