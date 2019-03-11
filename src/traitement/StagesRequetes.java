package traitement;

import contrat.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class StagesRequetes {

    private final StagesIO io;

    public StagesRequetes(StagesIO io) {
        this.io = io;
    }

    /**
     * Renvoie l'ensemble des étudiants de l'enseignant dont le nom est donné en paramètre.
     *
     * @param nom le nom de l'enseignant
     * @return l'ensemble de ses étudiants
     */
    public Set<contrat.Etudiant> etudiantsDeLEnseignant(String nom) {

        /*
        ======
         On récupère les enseignants dans stageIO,
         on convertit en stream pour pouvoir appliquer les filtres,
         on compare les noms avec celui passé en paramettre,
         on retourne les etudinants de l'enseignant.
        ======
         */
        contrat.Enseignant enseignant = io.getEnseignants().stream().filter(
                e -> e.getNom().equals(nom)
            ).findAny().get();

        return enseignant.getEtudiants();

    }

    /**
     * Renvoie les enseignants qui encadrent des stages d'une compétence donnée en paramètre.
     *
     * @param comp la compétence des stages dont on cherche l'encadrant
     * @return l'ensemble des enseignants qui encadrent des stages de cette compétence
     */
    public Set<contrat.Enseignant> enseignantEncadreCompetence(Competence comp) {

        /*
        =======
        On récupère les enseignants dans stageIO,
        on convertit en stream pour pouvoir appliquer les filtres,
        on parcours la liste des etudiants puis leurs stages,
        on compare les competences au paramettre et enfin
        on transforme le tream en Set qu'on retourne
        ========
         */
        return io.getEnseignants().stream().filter(
                enseignant ->  enseignant.getEtudiants().stream().anyMatch(
                        etu -> etu.getStages().stream().anyMatch(
                                stage -> stage.getCompetence().equals(comp)
                        )
                )
        ).collect(Collectors.toSet());
    }

    /**
     * Qui sont les étudiants n'ayant pas stage pouvant avoir au moins un stage selon leurs compétences
     * et celle du stage ?
     *
     * @return le mapping entre étudiant n'ayant pas de stage et les stages qu'on peut lui proposer,
     * selon ses compétences
     */
    public Map<Etudiant, Set<Stage>> etudiantsMatchStagesNonAffectes() {

        Set<Stage> stageNonAffecte = io.getStages().stream().filter(
                stage -> stage.getStatut().equals(Statut.NON_AFFECTE)
        ).collect(Collectors.toSet());
        
        Set<Etudiant> etuSansStage = io.getEtudiants().stream().filter(
                etu -> etu.getStages().isEmpty()
        ).collect(Collectors.toSet());

        Map<Etudiant,Set<Stage>> etudiantStages = new HashMap<>();

        for(Etudiant etu : etuSansStage){
            etudiantStages.put(etu,stageNonAffecte.stream().filter(
                    stage -> etu.getCompetences().contains(stage.getCompetence())
            ).collect(Collectors.toSet()));
        }
        return etudiantStages;
    }
}
