package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.entities.Formation;
import com.example.hrconnectpro.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/formations")
@RequiredArgsConstructor
public class FormationController {
    private final FormationService formationService;

    @GetMapping
    public ResponseEntity getAllFormations() {
        List<Formation> formations = formationService.getAllFormations();
        if(formations.isEmpty()) {
            return ResponseMessage.notFound("Formation not found");
        }else {
            return ResponseMessage.ok(formations, "Success");
        }
    }
    @PostMapping("/add")
    public ResponseEntity saveFormation(@RequestBody Formation formation) {
        Formation formation1 = formationService.saveFormation(formation);
        if(formation1 == null) {
            return ResponseMessage.badRequest("Formation not created");
        }else {
            return ResponseMessage.created(formation1, "Formation created successfully");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getFormationById(Long id) {
        return ResponseMessage.ok( formationService.findFormationById(id), "Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFormation(Long id) {
        formationService.deleteFormation(id);
        return ResponseMessage.ok(formationService.findFormationById(id), "Formation deleted successfully");
    }

}
