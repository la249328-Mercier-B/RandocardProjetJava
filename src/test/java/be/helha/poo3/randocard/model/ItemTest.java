package be.helha.poo3.randocard.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la hiérarchie Item")
class ItemTest {

    private Partie partie;

    @BeforeAll
    static void demarrageSuiteDeTests() {
        System.out.println("=== Début des tests de Item ===");
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de Item ===");
    }

    @BeforeEach
    void avantChaqueTest(TestInfo testInfo) {
        System.out.println("-- Début du test : " + testInfo.getDisplayName());
        partie = new Partie();
    }

    @AfterEach
    void apresChaqueTest(TestInfo testInfo) {
        System.out.println("-- Fin du test : " + testInfo.getDisplayName());
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

    @Test
    @DisplayName("Vérification complète de la structure Item et de son héritage")
    void testStructureEtHeritageItem() {

        Item item = new Item();
        item.setId("65f2a1b3c9e4d82a10f5b123");
        item.setNom("Objet Mystère");
        item.setDescription("Une description générique.");
        item.setCout(100);
        item.setImageUrl("http://cdn.game/item.png");

        assertEquals("65f2a1b3c9e4d82a10f5b123", item.getId());
        assertEquals("Objet Mystère", item.getNom());
        assertEquals(100, item.getCout());
        assertEquals("http://cdn.game/item.png", item.getImageUrl());

        MedKit medKit = new MedKit();
        medKit.setId("mk_001");
        medKit.setNom("Kit de soin");
        medKit.setCout(50);
        medKit.setImageUrl("medkit.png");

        assertEquals("mk_001", medKit.getId());
        assertEquals("Kit de soin", medKit.getNom());
        assertEquals(50, medKit.getCout());

        Shield shield = new Shield();
        shield.setId("sh_002");
        shield.setNom("Bouclier");
        shield.setImageUrl("shield.png");

        assertEquals("sh_002", shield.getId());
        assertEquals("Bouclier", shield.getNom());
        assertEquals("shield.png", shield.getImageUrl());

        ReverseUno reverse = new ReverseUno();
        reverse.setId("rev_003");
        reverse.setNom("Reverse Card");
        reverse.setDescription("Inverse la tendance");

        assertEquals("rev_003", reverse.getId());
        assertEquals("Reverse Card", reverse.getNom());
        assertEquals("Inverse la tendance", reverse.getDescription());
    }
}