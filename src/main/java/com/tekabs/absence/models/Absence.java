package com.tekabs.absence.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Absence
 */
@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "matricule")
    Student student;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    Matiere matiere;

    String dateEnregistrement;
    Boolean estAbsent;

    public Absence() {
    }

    public Absence(Long id, Student student, Matiere matiere, String dateEnregistrement, Boolean estAbsent) {
        this.id = id;
        this.student = student;
        this.matiere = matiere;
        this.dateEnregistrement = dateEnregistrement;
        this.estAbsent = estAbsent;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Matiere getMatiere() {
        return this.matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String getDateEnregistrement() {
        return this.dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Boolean isEstAbsent() {
        return this.estAbsent;
    }

    public Boolean getEstAbsent() {
        return this.estAbsent;
    }

    public void setEstAbsent(Boolean estAbsent) {
        this.estAbsent = estAbsent;
    }

    public Absence id(Long id) {
        this.id = id;
        return this;
    }

    public Absence student(Student student) {
        this.student = student;
        return this;
    }

    public Absence matiere(Matiere matiere) {
        this.matiere = matiere;
        return this;
    }

    public Absence dateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
        return this;
    }

    public Absence estAbsent(Boolean estAbsent) {
        this.estAbsent = estAbsent;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Absence)) {
            return false;
        }
        Absence absence = (Absence) o;
        return Objects.equals(id, absence.id) && Objects.equals(student, absence.student)
                && Objects.equals(matiere, absence.matiere)
                && Objects.equals(dateEnregistrement, absence.dateEnregistrement)
                && Objects.equals(estAbsent, absence.estAbsent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, matiere, dateEnregistrement, estAbsent);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", student='" + getStudent().getEmail() + "'" + ", matiere='"
                + getMatiere().getLabel() + "'" + ", dateEnregistrement='" + getDateEnregistrement() + "'"
                + ", estAbsent='" + isEstAbsent() + "'" + "}";
    }

}