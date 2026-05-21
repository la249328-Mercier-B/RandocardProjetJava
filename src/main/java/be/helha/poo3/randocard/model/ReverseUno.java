package be.helha.poo3.randocard.model;

import lombok.Data;

/**
 * The type Reverse uno.
 */
@Data
public class ReverseUno extends Item {
    /**
     * Instantiates a new Reverse uno.
     */
    public ReverseUno() {super();}

    @Override
    public void utiliser(Partie partie) {
        partie.changerCarteVisible();
    }
}