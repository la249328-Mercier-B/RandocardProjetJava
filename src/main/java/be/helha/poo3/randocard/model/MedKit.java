package be.helha.poo3.randocard.model;

import lombok.Data;

/**
 * The type Med kit.
 */
@Data
public class MedKit extends Item {
    /**
     * Instantiates a new Med kit.
     */
    public MedKit() { super(); }

    @Override
    public void utiliser(Partie partie) {
        partie.ajouterCoeur();
    }
}