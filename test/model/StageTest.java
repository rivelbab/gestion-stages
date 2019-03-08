package model;

import contrat.*;
import contrat.Entreprise;
import contrat.Stage;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class StageTest {

    private static final String MA_PETITE_ENTREPRISE_WEB = "Ma petite entreprise WEB";
    Stage stage ;
    Entreprise entreprise;

    @BeforeClass
    public void setUpClass(){
        entreprise = new model.Entreprise(MA_PETITE_ENTREPRISE_WEB);
        stage = new model.Stage("VoiciUnID", "Dev WEB", Competence.WEB, Niveau.L3, entreprise);
    }

    @AfterClass
    public void tearDownClass(){
        entreprise = null;
        stage = null;
    }

    @BeforeMethod
    public void setUp() {
        stage.setStatut(null);
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void statusShouldInitiallyBeNull() {
        System.out.println(stage.getStatut().name());
        //Assert.assertNull(stage.getStatut());
    }

    @Test
    public void statusShouldBeAsAssigned(){
        stage.setStatut(Statut.NON_AFFECTE);
        Assert.assertEquals(Statut.NON_AFFECTE, stage.getStatut());
    }
}