package com.example.hrconnectpro.controller;

import com.example.hrconnectpro.config.handlers.response.ResponseMessage;
import com.example.hrconnectpro.dto.CongeDTO;
import com.example.hrconnectpro.dto.response.CongeResponseDto;
import com.example.hrconnectpro.entities.Conge;
import com.example.hrconnectpro.enums.StatusConge;
import com.example.hrconnectpro.service.CongeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/conge")
@RequiredArgsConstructor

public class CongeController {
    private final CongeService congeService;

    @GetMapping
    public ResponseEntity getAllConge() {
        List<Conge> conges = congeService.getAllConges();
        if (conges.isEmpty()) {
            return ResponseMessage.notFound("Conge not found");
        }else {
            return ResponseMessage.ok(
                    conges.stream()
                            .map(CongeResponseDto::toDto)
                            .toList(),
                    "Success"
            );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getCongeById(@PathVariable Long id) {
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

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteConge(@PathVariable Long id) {
//        Conge conge = congeService.findCongeById(id);
//        if (conge == null) {
//            return ResponseMessage.badRequest("Failed to delete congé.");
//        }else {
//            return ResponseMessage.created(conge,"Congé deleted successfully");
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteConge(@PathVariable Long id) {
//        Conge conge = congeService.findCongeById(id);
//        if (conge == null) {
//            return ResponseEntity.badRequest().body("Failed to delete congé: Congé not found.");
//        } else {
//            congeService.deleteConge(id);
//            return ResponseEntity.ok().body("Congé deleted successfully");
//        }
//    }
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteConge(@PathVariable Long id) {
    Conge conge = congeService.findCongeById(id);
    if (conge == null) {
        return ResponseEntity.badRequest().body("Failed to delete congé: Congé not found.");
    } else {
        congeService.deleteConge(id);
        return ResponseEntity.ok().body("{\"message\":\"Congé deleted successfully\"}");
    }
}

    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id, @RequestParam StatusConge status) {
        Conge conge = congeService.updateStatus(id, status);
        return ResponseMessage.created(conge,"Status changed successfully");
    }

}
