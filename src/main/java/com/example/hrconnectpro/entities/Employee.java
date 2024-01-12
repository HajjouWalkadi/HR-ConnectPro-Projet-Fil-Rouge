package com.example.hrconnectpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
    private String dateNaissance;
    private String dateEmbauche;
    private String dateDepart;
    private String poste;
    private int salaire;
    private String departement;
    private String role;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "conge_id")
    private Conge conge;
}
