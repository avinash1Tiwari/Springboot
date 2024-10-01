package com.springCrud.example.SpringCrudOperations.Entities;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor  // Corrected annotation
@NoArgsConstructor
@Table(name = "SpringEmployee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;

    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private boolean isActive;


    public void setId(Long empId) {
        this.empId=empId;
    }
}
