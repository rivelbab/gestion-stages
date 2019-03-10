package traitement;

import contrat.Competence;
import contrat.Etudiant;
import contrat.Stage;
import contrat.Enseignant;

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

        Map<Etudiant, Set<Stage>> stageEtu = new HashMap<>();
        Set<Stage> stages = new HashSet<>(io.getStages());

        /*
        =========
         On récupère les étudiants
         On parcours leur stage
         On filtre sur les compétences de chaques stages de l'étudiant
         On compare aux compétences de l'étudiant
         On ajoute les correspondances dans le map
         On retourne enfin le map
        =========
         */
        io.getEtudiants().forEach(etu -> {
                stages.stream().filter(
                        s -> etu.getCompetences().contains(s.getCompetence())
                ).collect(Collectors.toSet());

                if(etu.getStages().isEmpty()) stageEtu.put(etu,stages);
            });

        return stageEtu;
    }
}
