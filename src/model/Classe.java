package model;

import contrat.Etudiant;
import contrat.Filiere;
import contrat.Niveau;

import java.util.Set;

public final class Classe implements contrat.Classe {

    private final Niveau niveau;
    private final Filiere filiere;
    private final String annee;
    private final Set<contrat.Etudiant> etudiants;


    public Classe(Niveau niveau, Filiere filiere, String annee) {
        this.niveau = niveau;
        this.filiere = filiere;
        this.annee = annee;
        //=== on initialise a un hashset vide ==
        this.etudiants = new HashSet<>();
    }


    @Override
    public Niveau getNiveau() {
        return niveau;
    }

    @Override
    public String getAnnee() {
        return annee;
    }

    @Override
    public Filiere getFiliere() {
        return filiere;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    @Override
    public boolean addEtudiants(Etudiant etu) {
        return etudiants.add(etu);
    }

    @Override
    public boolean removeEtudiant(Etudiant etu) {
        return etudiants != null && etudiants.remove(etu);
    }
}
