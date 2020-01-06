package com.tekabs.absence.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // This tells Hibernate to make a table out of this class
public class Student {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matricule;
    private String name;
    private String prenom;
    private String email;
    private String dateNaissance;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Classe studentClasse;

    @OneToMany(mappedBy = "student")
    private List<Absence> recordAbsence;


    public Student() {
    }

    public Student(Long matricule, String name, String prenom, String email, String dateNaissance, Classe studentClasse, List<Absence> recordAbsence) {
        this.matricule = matricule;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.studentClasse = studentClasse;
        this.recordAbsence = recordAbsence;
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

    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Classe getStudentClasse() {
        return this.studentClasse;
    }

    public void setStudentClasse(Classe studentClasse) {
        this.studentClasse = studentClasse;
    }

    public List<Absence> getRecordAbsence() {
        return this.recordAbsence;
    }

    public void setRecordAbsence(List<Absence> recordAbsence) {
        this.recordAbsence = recordAbsence;
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

    public Student dateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public Student studentClasse(Classe studentClasse) {
        this.studentClasse = studentClasse;
        return this;
    }

    public Student recordAbsence(List<Absence> recordAbsence) {
        this.recordAbsence = recordAbsence;
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
        return Objects.equals(matricule, student.matricule) && Objects.equals(name, student.name) && Objects.equals(prenom, student.prenom) && Objects.equals(email, student.email) && Objects.equals(dateNaissance, student.dateNaissance) && Objects.equals(studentClasse, student.studentClasse) && Objects.equals(recordAbsence, student.recordAbsence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, name, prenom, email, dateNaissance, studentClasse, recordAbsence);
    }

    @Override
    public String toString() {
        return "{" +
            " matricule='" + getMatricule() + "'" +
            ", name='" + getName() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", studentClasse='" + getStudentClasse() + "'" +
            ", recordAbsence='" + getRecordAbsence() + "'" +
            "}";
    }


}
