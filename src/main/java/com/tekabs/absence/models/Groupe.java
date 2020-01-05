package com.tekabs.absence.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupe_id")
    private Long id;
    private String label;
    private String nomComplet;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentClasse")
    private List<Student> classeStudents;

    @ManyToMany
    @JoinTable(name = "classe_matiere", joinColumns = @JoinColumn(name = "groupe_id"), inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    private List<Matiere> classeMatieres;

    public Groupe() {
    }

    public Groupe(Long id, String label, String nomComplet, List<Student> classeStudents,
            List<Matiere> classeMatieres) {
        this.id = id;
        this.label = label;
        this.nomComplet = nomComplet;
        this.classeStudents = classeStudents;
        this.classeMatieres = classeMatieres;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNomComplet() {
        return this.nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public List<Student> getClasseStudents() {
        return this.classeStudents;
    }

    public void setClasseStudents(List<Student> classeStudents) {
        this.classeStudents = classeStudents;
    }

    public List<Matiere> getClasseMatieres() {
        return this.classeMatieres;
    }

    public void setClasseMatieres(List<Matiere> classeMatieres) {
        this.classeMatieres = classeMatieres;
    }

    public Groupe id(Long id) {
        this.id = id;
        return this;
    }

    public Groupe label(String label) {
        this.label = label;
        return this;
    }

    public Groupe nomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
        return this;
    }

    public Groupe classeStudents(List<Student> classeStudents) {
        this.classeStudents = classeStudents;
        return this;
    }

    public Groupe classeMatieres(List<Matiere> classeMatieres) {
        this.classeMatieres = classeMatieres;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Groupe)) {
            return false;
        }
        Groupe groupe = (Groupe) o;
        return Objects.equals(id, groupe.id) && Objects.equals(label, groupe.label)
                && Objects.equals(nomComplet, groupe.nomComplet)
                && Objects.equals(classeStudents, groupe.classeStudents)
                && Objects.equals(classeMatieres, groupe.classeMatieres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, nomComplet, classeStudents, classeMatieres);
    }

    @Override
    public String toString() {
        String x;
        try {
            x = "{" + " id='" + getId() + "'" + ", label='" + getLabel() + "'" + ", nomComplet='" + getNomComplet()
                    + "'" + ", classeStudents='" + getClasseStudents().size() + "'" + ", classeMatieres='"
                    + getClasseMatieres().size() + "'" + "}";
        } catch (Exception e) {
            // System.out.println(getClasseMatieres());
            x = "><";
        }
        return x;
    }

}
