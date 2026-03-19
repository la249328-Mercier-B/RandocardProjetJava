package be.helha.poo3.randocard.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@Builder
@AllArgsConstructor
public class Carte {
    private int nbAdeviner;
    private int nbVisible;

    public Carte() {
        this.nbAdeviner = genererNbRandom();
        do {
            this.nbVisible = genererNbRandom();
        } while (this.nbVisible == nbAdeviner);
    }

    public int genererNbRandom() {
        Random rand = new Random();
        int nb = rand.nextInt(1,11);
        return nb;
    }

    public boolean verif(boolean reponseEntree) {
        // Réponse entrée:
        // True = Bouton plus grand
        // False = Bouton plus petit
        // -------------------------------------------------------------------
        // Si on return true: Bonne réponse ! On gagne des pièces et du score
        // Si on return false: Mauvaise réponse... On perd un coeur
        System.out.println(nbAdeviner);
        System.out.println(nbVisible);
        return reponseEntree == (nbVisible < nbAdeviner);
    }
}
