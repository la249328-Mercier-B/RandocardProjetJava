package be.helha.poo3.randocard.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Shield.
 */
@Data
public class Shield extends Item {
    /**
     * Instantiates a new Shield.
     */
    public Shield() {super();}

    @Override
    public void utiliser(Partie partie) {
        partie.activerBouclier();
    }
}