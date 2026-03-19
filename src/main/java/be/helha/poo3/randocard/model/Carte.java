package be.helha.poo3.randocard.model;
import lombok.Builder;
import lombok.Data;
import java.util.Random;

@Data
@Builder
public class Carte {
    private int nbAdeviner;
    private int nbVisible;

    public Carte() {
        this.nbAdeviner = genererNbRandom();
        this.nbVisible = genererNbRandom();
    }

    public int genererNbRandom() {
        Random rand = new Random();
        int nb = rand.nextInt(1,11);
        return nb;
    }

    public boolean verif(boolean reponseEntree){
        // True = Bouton plus grand
        // False = Bouton plus petit
        boolean reponseCorrecte;

        if (nbVisible < nbAdeviner){
            reponseCorrecte = false;
            // Si le nombre visible est plus petit que le nombre à deviner, la réponse est <
        }
        else {
            reponseCorrecte = true;
            // Si le nombre visible est plus grand que le nombre à deviner, la réponse est >
        }

        if (reponseEntree == reponseCorrecte){ // On vérifie la réponse entrée avec la réponse correcte
            return true; // Bonne réponse ! On gagne des pièces et du score
        } else {
            return false; // Mauvaise réponse... On perd un coeur
        }
    }
}
