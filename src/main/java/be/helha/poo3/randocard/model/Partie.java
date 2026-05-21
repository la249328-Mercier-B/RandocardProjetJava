package be.helha.poo3.randocard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

/**
 * The type Partie.
 */
@Data
@Builder
@AllArgsConstructor
public class Partie {
    private int nbAdeviner;
    private int nbVisible;
    private int nbCoeurs;
    private int nbCoeursMax;
    private boolean partieEnCours;
    private int scorePartie;
    private boolean bouclier;
    private int compterBouclier;

    /**
     * Instantiates a new Partie.
     */
    public Partie() {}

    /**
     * Lancer partie.
     */
    public void lancerPartie() {
        this.nbAdeviner = genererNbRandom();
        do {
            this.nbVisible = genererNbRandom();
        } while (this.nbVisible == nbAdeviner);

        this.nbCoeurs = 3;
        this.nbCoeursMax = 3;
        this.scorePartie = 0;
        this.partieEnCours = true;
        this.bouclier = false;
        System.out.println("Nb a deviner: " + nbAdeviner);
        System.out.println("Nb visible: " + nbVisible);
    }

    /**
     * Generer nb random int.
     *
     * @return the int
     */
    public int genererNbRandom() {
        Random rand = new Random();
        int nb = rand.nextInt(1, 11);
        return nb;
    }

    /**
     * Verif boolean.
     *
     * @param reponseEntree the reponse entree
     * @return the boolean
     */
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

    /**
     * Passer prochaine carte.
     */
    public void passerProchaineCarte() {

        if (compterBouclier > 0) {
            compterBouclier--;
        }
        if (compterBouclier == 0) {
            bouclier = false;
        }

        this.nbVisible = nbAdeviner;
        do {
            this.nbAdeviner = genererNbRandom();
        } while (this.nbVisible == nbAdeviner);
        System.out.println("Nb a deviner: " + nbAdeviner);
        System.out.println("Nb visible: " + nbVisible);
    }

    /**
     * Perdre un coeur.
     */
    public void perdreUnCoeur() {
        if (!bouclier){
            this.nbCoeurs--;
            System.out.println("Nb coeurs: " + nbCoeurs);
        } else{
            System.out.println("Bouclier actif ! Vous n'avez pas perdu de coeur.");
        }
        if (this.nbCoeurs == 0) {
            this.partieEnCours = false;
        }
    }

    /**
     * Ajouter coeur.
     */
    public void ajouterCoeur() {
        this.nbCoeurs++;
        if (this.nbCoeurs >= this.nbCoeursMax) {
            this.nbCoeursMax++;
        }
        System.out.println("Coeur ajouté ! Nombre de coeurs: " + nbCoeurs);
    }

    /**
     * Activer bouclier.
     */
    public void activerBouclier(){
        this.compterBouclier = 3;
        this.bouclier = true;
        System.out.println("Bouclier activé !");
    }

    /**
     * Changer carte visible.
     */
    public void changerCarteVisible() {

        int ancienneValeur = this.nbVisible;
        int nouvelleValeur;

        do {
            nouvelleValeur = genererNbRandom();
        } while (nouvelleValeur == ancienneValeur || nouvelleValeur == nbAdeviner);

        this.nbVisible = nouvelleValeur;
        System.out.println("Carte visible modifiée !");
    }
}
