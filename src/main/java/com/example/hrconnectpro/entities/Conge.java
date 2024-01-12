package com.example.hrconnectpro.entities;

import com.example.hrconnectpro.enums.TypeConge;
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
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String description;
    private String dateDebut;
    private String dateFin;
    private TypeConge typeConge;

    /*@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;*/

    @OneToMany(mappedBy = "conge")
    private List<Employee> employee;




}
