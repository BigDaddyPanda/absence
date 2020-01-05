package com.tekabs.absence.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // This tells Hibernate to make a table out of this class
public class Student {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matricule;

    private String name;

    private String prenom;

    private String email;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Groupe studentClasse;

    private String dateNaissance;

    public Student() {
        this.matricule = 0L;
        this.name = "Sample";
        this.prenom = "";
        this.email = "";
        this.dateNaissance = "2000/12/20";
        this.studentClasse = null;
    }

    public Student(Long matricule, String name, String prenom, String email, Groupe studentClasse,
            String dateNaissance) {
        this.matricule = matricule;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.studentClasse = studentClasse;
        this.dateNaissance = dateNaissance;
    }

    public Long getMatricule() {
        return this.matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Groupe getStudentClasse() {
        return this.studentClasse;
    }

    public void setStudentClasse(Groupe studentClasse) {
        this.studentClasse = studentClasse;
    }

    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Student matricule(Long matricule) {
        this.matricule = matricule;
        return this;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public Student prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public Student studentClasse(Groupe studentClasse) {
        this.studentClasse = studentClasse;
        return this;
    }

    public Student dateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(matricule, student.matricule) && Objects.equals(name, student.name)
                && Objects.equals(prenom, student.prenom) && Objects.equals(email, student.email)
                && Objects.equals(studentClasse, student.studentClasse)
                && Objects.equals(dateNaissance, student.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, name, prenom, email, studentClasse, dateNaissance);
    }

    @Override
    public String toString() {
        return "{" + " matricule='" + getMatricule() + "'" + ", name='" + getName() + "'" + ", prenom='" + getPrenom()
                + "'" + ", email='" + getEmail() + "'" + ", studentClasse='" + getStudentClasse().getLabel() + "'"
                + ", dateNaissance='" + getDateNaissance() + "'" + "}";
    }

}
