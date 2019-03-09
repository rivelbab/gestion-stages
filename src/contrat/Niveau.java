package contrat;

/**
 * Représente le niveau d'une classe.
 */
public enum Niveau {
    L3(1), M1(2), M2(3);

    private final int niveau;

    Niveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}