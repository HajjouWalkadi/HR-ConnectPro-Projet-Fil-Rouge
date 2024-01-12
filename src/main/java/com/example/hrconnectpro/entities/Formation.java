package com.example.hrconnectpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titre;
    private String description;
    private String dateDebut;
    private String dateFin;
    private String lieu;
    private String cours;

    @OneToMany(mappedBy = "formation")
    private List<Employee> employee;
}
