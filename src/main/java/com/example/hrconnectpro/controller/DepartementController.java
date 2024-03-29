package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.entities.Departement;
import com.example.hrconnectpro.repository.DepartementRepository;
import com.example.hrconnectpro.service.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/departement")
@RequiredArgsConstructor
public class DepartementController {
    private final DepartementService departementService;
    private final DepartementRepository departementRepository;

    @GetMapping
    public ResponseEntity getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();
        if (departements.isEmpty()) {
            return ResponseMessage.notFound("Departement not found");
        } else {
            return ResponseMessage.ok(departements, "Success");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getDepartementById(Long id) {
        return ResponseMessage.ok(departementService.getDepartementById(id), "Success");
    }

    @PostMapping("/add")
    public ResponseEntity addDepartement(@RequestBody Departement departement) {
        Departement departement1 = departementService.addDepartement(departement);
        if (departement1 == null) {
            return ResponseMessage.badRequest("Departement not created");
        } else {
            return ResponseMessage.created(departement1, "Departement created successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartement(@PathVariable Long id) {
        Departement departement = departementService.getDepartementById(id);
        if (departement == null) {
            return ResponseEntity.badRequest().body("Failed to delete department: Department not found.");
        } else {
           departementService.deleteDepartement(id);
            return ResponseEntity.ok().body("{\"message\":\"Department deleted successfully\"}");
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> getDepartementsCount() {
        Long count = departementService.getDepartementsCount();
        if (count == null) {
            return ResponseEntity.badRequest().body("Failed to get department Count.");
        } else {
            return ResponseEntity.ok().body(count);
        }
    }







}
