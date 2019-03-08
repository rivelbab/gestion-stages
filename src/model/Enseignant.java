package model;

import contrat.Etudiant;

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
        return null;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return null;
    }

    @Override
    public boolean addEtudiant(Etudiant etu) {
        return false;
    }
}
