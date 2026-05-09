package be.helha.poo3.randocard.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la hiérarchie Item (Sans Mockito)")
class ItemTest {

    private Partie partie;

    @BeforeEach
    void setup() {
        partie = new Partie();
    }

    @Test
    @DisplayName("MedKit doit augmenter le nombre de cœurs de la partie de 1")
    void utiliserMedKitTest() {
        partie.lancerPartie();

        MedKit medKit = new MedKit();

        medKit.utiliser(partie);

        assertEquals(4, partie.getNbCoeurs());
    }

    @Test
    @DisplayName("Shield doit activer le bouclier")
    void utiliserShieldTest() {
        partie.lancerPartie();

        Shield shield = new Shield();

        shield.utiliser(partie);

        assertTrue(partie.isBouclier());
        assertEquals(3, partie.getCompterBouclier());
    }

    @Test
    @DisplayName("ReverseUno doit modifier le numéro de la carte visible")
    void utiliserReverseUnoTest() {

        partie.lancerPartie();

        int ancienneCarte = partie.getNbVisible();
        ReverseUno reverse = new ReverseUno();

        reverse.utiliser(partie);

        assertNotEquals(ancienneCarte, partie.getNbVisible());
    }

    @Test
    @DisplayName("L'appel de utiliser() sur un Item générique ne modifie rien")
    void utiliserItemGeneriqueTest() {

        partie.lancerPartie();

        Item itemGenerique = new Item();

        int coeursAvant = partie.getNbCoeurs();
        boolean bouclierAvant = partie.isBouclier();
        int nbVisibleAvant = partie.getNbVisible();

        itemGenerique.utiliser(partie);

        assertEquals(coeursAvant, partie.getNbCoeurs());
        assertEquals(bouclierAvant, partie.isBouclier());
        assertEquals(nbVisibleAvant, partie.getNbVisible());
    }
}