package be.helha.poo3.randocard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
@AllArgsConstructor
public class Partie {
    private int nbAdeviner;
    private int nbVisible;
    private int nbCoeurs = 3;
    private boolean partieEnCours = true;
    private int scorePartie = 0;

    public Partie() {
        this.nbAdeviner = genererNbRandom();
        do {
            this.nbVisible = genererNbRandom();
        } while (this.nbVisible == nbAdeviner);
    }

    public int genererNbRandom() {
        Random rand = new Random();
        int nb = rand.nextInt(1, 11);
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

    public void passerProchaineCarte() {
        this.nbVisible = nbAdeviner;
        do {
            this.nbAdeviner = genererNbRandom();
        } while (this.nbVisible == nbAdeviner);
        System.out.println("Nb a deviner: " + nbAdeviner);
        System.out.println("Nb visible: " + nbVisible);
    }

    public void perdreUnCoeur() {
        this.nbCoeurs--;
        System.out.println("Nb coeurs: " + nbCoeurs);
        if (this.nbCoeurs == 0) {
            this.partieEnCours = false;
        }
    }
}
