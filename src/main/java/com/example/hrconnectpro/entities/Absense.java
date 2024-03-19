package com.example.hrconnectpro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate

public class Absense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  private Employee employee;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  @Column
  private String date;

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}
// give the form of the entity Absence to test it postman format json
// {
//     "date": "2021-09-01"
// }
