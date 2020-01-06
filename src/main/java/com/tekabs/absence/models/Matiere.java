package com.tekabs.absence.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matiere_id")
    private long id;
    private String label;
    private float nombreHeure;

    @ManyToMany(mappedBy = "classeMatieres")
    private List<Classe> matiereClasses;

    @OneToMany(mappedBy = "matiere")
    private List<Absence> listeAppel;

    public Matiere() {
    }

    public Matiere(long id, String label, float nombreHeure, List<Classe> matiereClasses, List<Absence> listeAppel) {
        this.id = id;
        this.label = label;
        this.nombreHeure = nombreHeure;
        this.matiereClasses = matiereClasses;
        this.listeAppel = listeAppel;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getNombreHeure() {
        return this.nombreHeure;
    }

    public void setNombreHeure(float nombreHeure) {
        this.nombreHeure = nombreHeure;
    }

    public List<Classe> getMatiereClasses() {
        return this.matiereClasses;
    }

    public void setMatiereClasses(List<Classe> matiereClasses) {
        this.matiereClasses = matiereClasses;
    }

    public List<Absence> getListeAppel() {
        return this.listeAppel;
    }

    public void setListeAppel(List<Absence> listeAppel) {
        this.listeAppel = listeAppel;
    }

    public Matiere id(long id) {
        this.id = id;
        return this;
    }

    public Matiere label(String label) {
        this.label = label;
        return this;
    }

    public Matiere nombreHeure(float nombreHeure) {
        this.nombreHeure = nombreHeure;
        return this;
    }

    public Matiere matiereClasses(List<Classe> matiereClasses) {
        this.matiereClasses = matiereClasses;
        return this;
    }

    public Matiere listeAppel(List<Absence> listeAppel) {
        this.listeAppel = listeAppel;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Matiere)) {
            return false;
        }
        Matiere matiere = (Matiere) o;
        return id == matiere.id && Objects.equals(label, matiere.label) && nombreHeure == matiere.nombreHeure && Objects.equals(matiereClasses, matiere.matiereClasses) && Objects.equals(listeAppel, matiere.listeAppel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, nombreHeure, matiereClasses, listeAppel);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", label='" + getLabel() + "'" +
            ", nombreHeure='" + getNombreHeure() + "'" +
            ", matiereClasses='" + getMatiereClasses() + "'" +
            ", listeAppel='" + getListeAppel() + "'" +
            "}";
    }

}
