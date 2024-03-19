package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.entities.Departement;
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





}
