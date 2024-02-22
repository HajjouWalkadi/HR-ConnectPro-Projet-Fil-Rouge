package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.dto.CongeDTO;
import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.service.CongeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conge")
@RequiredArgsConstructor

public class CongeController {
    private final CongeService congeService;

    @GetMapping
    public ResponseEntity getAllConge() {
        List<Conge> conges = congeService.getAllConges();
        if (conges.isEmpty()) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok(conges,"Success");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getCongeById(Long id) {
        Conge conge = congeService.findCongeById(id);
        if(conge == null) {
            return ResponseMessage.notFound("Congé not found");
        }else {
            return ResponseMessage.ok(conge,"Success");
        }
    }

    public ResponseEntity getCongeByEmployeeId(Long id) {
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity addConge(@RequestBody @Valid CongeDTO congeDTO) {
        Conge conge1 = congeService.saveConge(congeDTO.toConge());
        if (conge1 == null) {
            return ResponseMessage.badRequest("Failed to create congé.");
        }else {
            return ResponseMessage.created(conge1,"Congé added successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateConge(@RequestBody @Valid CongeDTO congeDTO, Long id) {
        Conge conge = congeService.updateConge(id, congeDTO.toConge());
        if (conge == null) {
            return ResponseMessage.badRequest("Failed to update congé.");
        }else {
            return ResponseMessage.created(conge,"Congé created successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConge(Long id) {
        Conge conge = congeService.findCongeById(id);
        if (conge == null) {
            return ResponseMessage.badRequest("Failed to delete congé.");
        }else {
            return ResponseMessage.created(conge,"Congé deleted successfully");
        }
    }

}
