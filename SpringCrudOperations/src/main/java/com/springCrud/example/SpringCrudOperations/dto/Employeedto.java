package com.springCrud.example.SpringCrudOperations.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Employeedto {

    private int empId;

    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private boolean isActive;

    public Employeedto() {
    }
    public Employeedto(int empId, String name, String email, LocalDate dateOfJoining, boolean isActive)
    {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining =  dateOfJoining;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getEmpId()
    {
        return this.empId;
    }
    public String getName()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }

    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }

    public boolean getStatus()
    {
        return this.isActive;
    }
}


