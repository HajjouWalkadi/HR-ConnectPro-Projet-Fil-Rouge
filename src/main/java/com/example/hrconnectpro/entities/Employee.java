package com.example.hrconnectpro.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String telephone;
    private Date dateNaissance;
    private Date dateEmbauche;
    private Date dateDepart;
    private int salaire;

    @ManyToOne
    private Role role;

    private String refreshToken;

    @ManyToOne
    private Poste poste;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_formation",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id"))
    private List<Formation> formations;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<Conge> conges;


    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @OneToMany(mappedBy = "employee")
    private List<Absense> absenses;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = role.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//                .collect(Collectors.toList());
//
//        // Include the role name itself as an authority
//        authorities.add(new SimpleGrantedAuthority(role.getName()));
//
//        return authorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

//give me the format json of the employee entity to test it in postman
// {
//   "id": 1,
//   "firstName": "string",
//   "lastName": "string",
//   "username": "string",
//   "email": "string",
//   "password": "string",
//   "telephone": "string",
//   "dateNaissance": "2022-03-02T14:00:00.000Z",
//   "dateEmbauche": "2022-03-02T14:00:00.000Z",
//   "dateDepart": "2022-03-02T14:00:00.000Z",
//   "salaire": 0,
//   "role": {
//     "id": 0,
//     "name": "string",
//     "authorities": [
//       {
//         "id": 0,
//         "name": "string"

//       }
//     ]
//   },
//   "refreshToken": "string",
//   "poste": {
//     "id": 0,
//     "nom": "string"
//   },
//   "formations": [
//     {
//       "id": 0,
//       "nom": "string",
//       "dateDebut": "2022-03-02T14:00:00.000Z",
//       "dateFin": "2022-03-02T14:00:00.000Z",
//       "employeeId": 0
//     }
//   ],
//   "conges": [
//     {
//       "id": 0,
//       "dateDebut": "2022-03-02T14:00:00.000Z",
//       "dateFin": "2022-03-02T14:00:00.000Z",
//       "employeeId": 0,
//       "typeConge": "string"
//     }
//   ],
//   "departement": {
//     "id": 0,
//     "nom": "string"
//   },
//   "absenses": [
//     {
//       "id": 0,
//       "dateDebut": "2022-03-02T14:00:00.000Z",
//       "dateFin": "2022-03-02T14:00:00.000Z",
//       "employeeId": 0,
//       "typeAbsense": "string"
//     }
//   ]
// }
