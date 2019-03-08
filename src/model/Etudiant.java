package model;

import contrat.Competence;
import contrat.Enseignant;
import contrat.Stage;

import java.util.*;

public final class Etudiant implements contrat.Etudiant {

    private final String nom;
    private final Set<contrat.Stage> stages;
    private final List<Competence> competences;
    private contrat.Enseignant tuteur;


    public Etudiant(String nom) {
        this.nom = null;
        this.stages = null;
        competences = null;
    }


    @Override
    public String getNom() {
        return null;
    }

    @Override
    public Set<Stage> getStages() {
        return null;
    }

    @Override
    public List<Competence> getCompetences() {
        return null;
    }

    @Override
    public boolean addStage(Stage stage) {
        return false;
    }

    @Override
    public boolean addCompetence(Competence competence) {
        return false;
    }

    @Override
    public Enseignant getTuteur() {
        return null;
    }

    @Override
    public void setTuteur(Enseignant tuteur) {

    }
}
