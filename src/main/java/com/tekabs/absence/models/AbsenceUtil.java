package com.tekabs.absence.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Absence Utilitaire to pass into form
 */
public class AbsenceUtil {
    private ArrayList<Absence> listeAbsence;
    private String dateEnregistrement;

    public AbsenceUtil(Iterable<Student> groupeEtudiant, Matiere matiere) {
        this.listeAbsence = new ArrayList<>();
        for (Student student : groupeEtudiant) {
            Absence v = (new Absence(null, student, matiere, "", false));
            // System.out.println(">>> listeAbsence.add" + v);
            this.listeAbsence.add(v);
        }
        this.dateEnregistrement = "";
    }

    public AbsenceUtil() {
    }

    public AbsenceUtil(ArrayList<Absence> listeAbsence, String dateEnregistrement) {
        this.listeAbsence = listeAbsence;
        this.dateEnregistrement = dateEnregistrement;
    }

    public ArrayList<Absence> getListeAbsence() {
        return this.listeAbsence;
    }

    public void setListeAbsence(ArrayList<Absence> listeAbsence) {
        this.listeAbsence = listeAbsence;
    }

    public String getDateEnregistrement() {
        return this.dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public AbsenceUtil listeAbsence(ArrayList<Absence> listeAbsence) {
        this.listeAbsence = listeAbsence;
        return this;
    }

    public AbsenceUtil dateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AbsenceUtil)) {
            return false;
        }
        AbsenceUtil absenceUtil = (AbsenceUtil) o;
        return Objects.equals(listeAbsence, absenceUtil.listeAbsence)
                && Objects.equals(dateEnregistrement, absenceUtil.dateEnregistrement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listeAbsence, dateEnregistrement);
    }

    @Override
    public String toString() {
        return "{" + " listeAbsence='" + getListeAbsence() + "'" + ", dateEnregistrement='" + getDateEnregistrement()
                + "'" + "}";
    }

}