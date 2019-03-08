package traitement;

import contrat.Enseignant;
import contrat.Etudiant;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * Permet de charger les fichiers contenant les donnees des etudiants et des stages.
 * Parse et donne accès à ces données en mémoire.
 */
public final class StagesIOMock {

    private final Map<String, contrat.Stage> stagesMap;
    private final Map<String, Etudiant> etusMap;
    private final Map<Integer, contrat.Classe> classesMap;
    private final Map<String, Enseignant> enseignantsMap;
    /**
     * Représente le chemin du fichier des données étudiants.
     */
    private Path etuFilePath;
    /**
     * Représente le chemin du fichier des données stages.
     */
    private Path stagesFilePath;


    public StagesIOMock(Path etuFilePath, Path stagesFilePath) {
        this.etuFilePath = null;
        this.stagesFilePath = null;
        this.stagesMap = null;
        this.etusMap = null;
        this.classesMap = null;
        this.enseignantsMap = null;
    }

    /**
     * Charge les données étudiants + stages des fichiers specifiés en paramètres au constructeur de cette classe.
     * @throws IOException
     */
    public void chargerDonnees() throws IOException {

    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses() {
        return null;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<Enseignant> getEnseignants(){

        List<Enseignant> enseignants = new ArrayList<>();

        for (int i= 0; i < 5; i++) {
            Enseignant  e = new model.Enseignant(UUID.randomUUID().toString());
            enseignants.add(e);
        }

        return enseignants;
    }

    public Set<contrat.Entreprise> getEntreprises(){
        return null;
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     * @return la liste des étudiants
     */
    public List<Etudiant> getEtudiants(){
        return null;
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){
        return null;
    }

}
