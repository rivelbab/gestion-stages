package model;

import contrat.Etudiant;

import java.util.HashSet;
import java.util.Set;

public final class Enseignant implements contrat.Enseignant {

    private final String nom;
    private final Set<contrat.Etudiant> etudiants;

    public Enseignant(String nom) {
        this.nom = nom;
        this.etudiants = new HashSet<>();
    }


    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    @Override
    public boolean addEtudiant(Etudiant etu) {
        return etudiants.add(etu);
    }

    @Override
    public String toString() {
        return "Enseignant { " +
                "nom = ' " + nom +
                " ' }";
    }
}
