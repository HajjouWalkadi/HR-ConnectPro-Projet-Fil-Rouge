package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.entities.Departement;
import com.example.hrconnectpro.entities.Poste;
import com.example.hrconnectpro.service.PosteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/poste")
@RequiredArgsConstructor
public class PosteController {
    private final PosteService posteService;

    @GetMapping
    public ResponseEntity getAllPostes() {
        List<Poste> postes = posteService.getAllPostes();
        if(postes.isEmpty()) {
            return ResponseMessage.notFound("Poste not found");
        }else {
            return ResponseMessage.ok(postes, "Success");
        }
    }

    @PostMapping("/add")
    public ResponseEntity addPoste(@RequestBody Poste poste) {
        Poste poste1 = posteService.addPoste(poste);
        if(poste1 == null) {
            return ResponseMessage.badRequest("Poste not created");
        }else {
            return ResponseMessage.created(poste1, "Poste created successfully");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPosteById(@PathVariable Long id) {
        return ResponseMessage.ok( posteService.getPosteById(id), "Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoste(@PathVariable Long id) {
        Poste poste = posteService.getPosteById(id);
        if (poste == null) {
            return ResponseEntity.badRequest().body("Failed to delete poste: Poste not found.");
        } else {
            posteService.deletePoste(id);
            return ResponseEntity.ok().body("{\"message\":\"Poste deleted successfully\"}");
        }
    }
}
