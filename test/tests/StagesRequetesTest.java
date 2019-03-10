package tests;

import contrat.Competence;
import traitement.StagesRequetes;
import traitement.StagesIO;
import model.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.testng.Assert.*;

public class StagesRequetesTest {

    private static final String ETU_FILE = "etudiants.txt";
    private static final String STAGES_FILE = "stages.txt";
    private static final String DONNEES = "donnees";

    StagesRequetes requetes;

    @BeforeMethod
    public void setUp() {
        StagesIO sio = new StagesIO(Paths.get(DONNEES, ETU_FILE), Paths.get(DONNEES, STAGES_FILE));
        requetes = new StagesRequetes(sio);
    }

    @AfterMethod
    public void tearDown() {
        requetes = null;
    }

    @Parameters({"nomTuteur", "sesEtudiants"})
    @Test
    public void testEtudiantsDeLEnseignant(String nomTuteur, String sesEtudiants) {
        assertEquals(requetes.etudiantsDeLEnseignant(nomTuteur), sesEtudiants);
    }

    @Parameters({"competence", "sesEnseignants"})
    @Test
    public void testEnseignantEncadreCompetence(String competence, String sesEnseignants) {
        assertEquals(requetes.enseignantEncadreCompetence(Competence.valueOf(competence)), sesEnseignants);
    }


    @Test
    public void testEtudiantsMatchStagesNonAffectes() {

    }
}