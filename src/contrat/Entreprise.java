package contrat;

import java.util.Set;


/**
 * Specifie le contrat d'un objet représentant une entreprise.
 */
public interface Entreprise {

    String getNom();

    Set<contrat.Stage> getStages();

    boolean addStage(contrat.Stage stage);
}
