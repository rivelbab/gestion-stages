package model;

import java.util.Set;

public final class Entreprise implements contrat.Entreprise {

    private final String nom;
    private final Set<Stage> stages;

    public Entreprise(String nom) {
        this.nom = nom;
        this.stages = new HashSet<>();;
    }


    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Set<Stage> getStages() {
        return stages;
    }

    @Override
    public boolean addStage(Stage stage) {
        return stages.add(stage);
    }
}
