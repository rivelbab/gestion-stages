package tests;

import contrat.Competence;
import org.testng.annotations.*;
import traitement.StagesRequetes;
import traitement.StagesIO;
import model.*;

import java.io.IOException;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class StagesRequetesTest {

    private static final String ETU_FILE = "etudiants.txt";
    private static final String STAGES_FILE = "stages.txt";
    private static final String DONNEES = "donnees";

    StagesRequetes requetes;
    StagesIO sio;

    @BeforeMethod
    public void setUp() throws IOException {
        sio = new StagesIO(Paths.get(DONNEES, ETU_FILE), Paths.get(DONNEES, STAGES_FILE));
        sio.chargerDonnees();
        requetes = new StagesRequetes(sio);
    }

    @AfterMethod
    public void tearDown() {
        requetes = null;
        sio = null;
    }

    @Parameters({"nom", "sesEtudiants"})
    @Test
    public void testEtudiantsDeLEnseignant(String nom, String sesEtudiants) {
        assertEquals(requetes.etudiantsDeLEnseignant(nom), sesEtudiants);
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