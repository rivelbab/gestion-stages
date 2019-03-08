package model;

import contrat.Entreprise;
import contrat.Etudiant;
import contrat.*;

public final class Stage implements contrat.Stage {
    private final String identifiant;
    private final String titre;
    private final Competence competence;
    private final Niveau niveau;
    private final contrat.Entreprise entreprise;
    private contrat.Etudiant etudiant;
    private Statut statut;

    public Stage(String identifiant, String titre, Competence competence, Niveau niveau,
                 contrat.Entreprise entreprise) {
        this.identifiant = null;
        this.titre = null;
        this.competence = null;
        this.niveau = null;
        this.entreprise = null;

    }


    @Override
    public String getIdentifiant() {
        return null;
    }

    @Override
    public Etudiant getEtudiant() {
        return null;
    }

    @Override
    public void setEtudiant(Etudiant etudiant) {

    }

    @Override
    public String getTitre() {
        return null;
    }

    @Override
    public Competence getCompetence() {
        return null;
    }

    @Override
    public Niveau getNiveau() {
        return null;
    }

    @Override
    public Entreprise getEntreprise() {
        return null;
    }

    @Override
    public Statut getStatut() {
        return this.statut;
    }

    @Override
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
