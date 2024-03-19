package com.example.hrconnectpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToMany(mappedBy = "formations", fetch = FetchType.LAZY)
    private List<Employee> employees;
}
