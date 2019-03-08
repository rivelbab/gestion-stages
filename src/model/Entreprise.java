package model;

import java.util.Set;

public final class Entreprise implements contrat.Entreprise {

    private final String nom;
    private final Set<Stage> stages;

    public Entreprise(String nom) {
        this.nom = null;
        this.stages = null;
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
    public boolean addStage(Stage stage) {
        return false;
    }
}
