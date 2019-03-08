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
public final class StagesIO {

    //== declaration des variables pour parser les fichiers ==
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String HASH_KEY = "#";

    private final Map<String, contrat.Stage> stagesMap;
    private final Map<String, contrat.Etudiant> etudiantsMap;
    private final Map<Integer, contrat.Classe> classesMap;
    private final Map<String, contrat.Enseignant> enseignantsMap;
    /**
     * Représente le chemin du fichier des données étudiants.
     */
    private Path etuFilePath;
    /**
     * Représente le chemin du fichier des données stages.
     */
    private Path stagesFilePath;


    public StagesIO(Path etuFilePath, Path stagesFilePath) {
        this.etuFilePath = etuFilePath;
        this.stagesFilePath = stagesFilePath;
        this.stagesMap = new HashMap<>();
        this.etudiantsMap = new HashMap<>();
        this.classesMap = new HashMap<>();
        this.enseignantsMap = new HashMap<>();
    }

    /**
     * Charge les données étudiants + stages des fichiers specifiés en paramètres au constructeur de cette classe.
     * @throws IOException
     */
    public void chargerDonnees() throws IOException {
        /* == Chargement des données stages ===
            On parcours le fichier line par line à la JavaScript
            On stock le tout dans les variables puis
            On peuple notre map et hop le tour est joué
         */
        Files.lines(stagesFilePath).forEach(line -> {
            //== les donnees sont separees par des #
            String[] datas = line.split(HASH_KEY);

            //== datas est un tableau contenant nos donnees, reste plus qu'a les recup
            val id = datas[0];
            val titre = datas[1];
            val competence = Competence.valueOf(datas[2]);
            val niveau = Niveau.valueOf(datas[3]);
            val statut = Statut.valueOf(datas[5]);
            val entreprise = new Entreprise(datas[4]);

            val stage = new Stage(id, titre, competence, niveau, entreprise);
            stage.statut = statut;

            stagesMap[id] = stage;
        });

        /*
            === on fait la meme chose pour etudiant
         */
        Files.lines(etuFilePath).forEach(line -> {
            String[] datas = line.split(HASH_KEY);

            val nom = datas[0];
            //== on verifie si ce nom n'existe pas deja puis on l'ajoute
            if !etudiantsMap.containsKey(nom) {
                etudiantsMap[nom] = new Etudiant(nom);
            }
            val etu = etudiantsMap[nom];
            val niveau = Niveau.valueOf(datas[1]);
            val filiere = Filiere.valueOf(datas[2]);
            val annee = datas[3];
            /* TODO : continuer l'extraction des donnees ici */
        });


    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses() {

        if (classesMap != null) {
            return new ArrayList<>(classesMap.values());
        }
        return null;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<contrat.Enseignant> getEnseignants(){

        if (enseignantsMap != null) {
            return  new ArrayList<>(enseignantsMap.values());
        }
        return null;
    }

    public Set<contrat.Entreprise> getEntreprises(){
        return null;
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     * @return la liste des étudiants
     */
    public List<contrat.Etudiant> getEtudiants(){

        if (etudiantsMap != null) {
            return new ArrayList<>(etudiantsMap.values());
        }
        return null;
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){

        if (stagesMap != null) {
            return new ArrayList<>(stagesMap.values());
        }
        return null;
    }

}
