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
            final String id = datas[0];
            final String titre = datas[1];
            final Competence competence = Competence.valueOf(datas[2]);
            final Niveau niveau = Niveau.valueOf(datas[3]);
            final String statut = Statut.valueOf(datas[5]);
            final String entreprise = new Entreprise(datas[4]);

            Stage stage = new Stage(id, titre, competence, niveau, entreprise);
            stage.statut = statut;

            stagesMap[id] = stage;
        });

        /*
            === on fait la meme chose pour etudiant
         */
        Files.lines(etuFilePath).forEach(line -> {

            String[] datas = line.split(HASH_KEY);

            final String nom = datas[0];
            final Niveau niveau = Niveau.valueOf(datas[1]);
            final Filiere filiere = Filiere.valueOf(datas[2]);
            final String annee = datas[3];
            //== les competences sont séparées par des virgules ==
            String[] competences = datas[4].split(COMMA);
            final String stageId = datas[5];
            final String nomTuteur = datas[6];

            //== on verifie si ce nom n'existe pas deja puis on l'ajoute
            if !etudiantsMap.containsKey(nom) {
                etudiantsMap[nom] = new Etudiant(nom);
            }
            Etudiant etu = etudiantsMap[nom];
            //=== on ajoute les competences ===
            if (competences.length > 0) {
                for(int i=0; i < competences.length; i++) {
                    Competence comp = Competence.valueOf(competences[i])
                    etu.addCompetence(comp);
                }
            }
            //=== on ajoute les stages de l'etudiant ===
            if (this.stagesMap.get(stageId) != null) etu.addStage(this.stagesMap.get(stageId));

            Classe mclasse = new Classe(niveau, filiere, annee);
            mclasse.addEtudiants(etu);

            classesMap[niveau] = mclasse;

            if !enseignantsMap.containsKey(nomTuteur) {
                enseignantsMap[nomTuteur] = new Enseignant(nomTuteur);
            }

            Enseignant tuteur = enseignantsMap[nomTuteur];
            //== on affecte un tuteur à l'étudiant et un étudiant au tuteur ===
            tuteur.addEtudiant(etu);
            etu.setTuteur(tuteur);

        });
    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses() {

        List<contrat.Classe> classes = this.classesMap.values().stream()
                .sorted(Comparator.comparing(contrat.Classe :: getNiveau))
                .collect(Collectors.toList());

        return classes;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<contrat.Enseignant> getEnseignants(){

        List<contrat.Enseignant> enseignants = this.enseignantsMap.values().stream()
                .sorted(Comparator.comparing(contrat.Enseignant :: getNom))
                .collect(Collectors.toList());

        return enseignants;
    }

    public Set<contrat.Entreprise> getEntreprises(){
        return null;
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     * @return la liste des étudiants
     */
    public List<contrat.Etudiant> getEtudiants(){

        List<contrat.Etudiant> etudiants = this.etudiantsMap.values().stream()
                .sorted(Comparator.comparing(contrat.Etudiant::getNom))
                .collect(Collectors.toList());

        return etudiants;
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){

        List<contrat.Stage> stages = this.stagesMap.values().stream()
                .sorted(Comparator.comparing(contrat.Stage::getNiveau))
                .collect(Collectors.toList());

        return stages;
    }

}
