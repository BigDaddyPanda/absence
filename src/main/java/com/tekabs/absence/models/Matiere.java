package com.tekabs.absence.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String label;
    private float nombreHeure;

    public Matiere(long id, String label, float nombreHeure) {
        this.id = id;
        this.label = label;
        this.nombreHeure = nombreHeure;
    }

    public Matiere() {
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getNombreHeure() {
        return nombreHeure;
    }

    public void setNombreHeure(float nombreHeure) {
        this.nombreHeure = nombreHeure;
    }
}
