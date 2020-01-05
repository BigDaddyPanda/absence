package com.tekabs.absence.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matricule;

    private String name;

    private String prenom;

    private String email;

    public String dateNaissance;

    public Student() {
        this.matricule = 0L;
        this.name = "Sample";
        this.prenom = "";
        this.email = "";
        this.dateNaissance = "2000/12/20";
    }

    public Student(String name, String prenom, String email, String dateNaissance) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

    public Long getMatricule() {
        return matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return dateNaissance;
    }

    public void setDate(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
