package be.helha.poo3.randocard.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la logique de Partie")
class PartieTest {

    private Partie partie;

    @BeforeAll
    static void demarrageSuiteDeTests() {
        System.out.println("=== Début des tests de Partie ===");
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de Partie ===");
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
    @DisplayName("lancerPartie doit initialiser correctement les valeurs par défaut")
    void lancerPartieTest() {
        partie.lancerPartie();

        assertEquals(3, partie.getNbCoeurs());
        assertEquals(3, partie.getNbCoeursMax());
        assertTrue(partie.isPartieEnCours());
        assertNotEquals(partie.getNbVisible(), partie.getNbAdeviner());
    }

    @Test
    @DisplayName("ajouterCoeur doit augmenter nbCoeurs et ajuster nbCoeursMax si nécessaire")
    void ajouterCoeurTest() {
        partie.lancerPartie();

        partie.ajouterCoeur();

        assertEquals(4, partie.getNbCoeurs());
        assertEquals(4, partie.getNbCoeursMax());
    }

    @Test
    @DisplayName("perdreUnCoeur doit décrémenter nbCoeurs si le bouclier est inactif")
    void perdreUnCoeurSansBouclierTest() {
        partie.lancerPartie();
        partie.perdreUnCoeur();

        assertEquals(2, partie.getNbCoeurs());
        assertTrue(partie.isPartieEnCours());
    }

    @Test
    @DisplayName("perdreUnCoeur ne doit rien faire si le bouclier est actif")
    void perdreUnCoeurAvecBouclierTest() {
        partie.lancerPartie();
        partie.activerBouclier();

        partie.perdreUnCoeur();

        assertEquals(3, partie.getNbCoeurs());
    }

    @Test
    @DisplayName("La partie doit s'arrêter quand nbCoeurs atteint 0")
    void finDePartieTest() {
        partie.lancerPartie();

        partie.perdreUnCoeur();
        partie.perdreUnCoeur();
        partie.perdreUnCoeur();

        assertEquals(0, partie.getNbCoeurs());
        assertFalse(partie.isPartieEnCours());
    }

    @Test
    @DisplayName("verif doit retourner true si la prédiction est correcte")
    void verifTest() {

        partie.setNbVisible(5);
        partie.setNbAdeviner(8);

        assertTrue(partie.verif(true));

        partie.setNbVisible(8);
        partie.setNbAdeviner(2);

        assertFalse(partie.verif(true));
    }

    @Test
    @DisplayName("activerBouclier doit initialiser le compteur à 3")
    void activerBouclierTest() {
        partie.activerBouclier();

        assertTrue(partie.isBouclier());
        assertEquals(3, partie.getCompterBouclier());
    }

    @Test
    @DisplayName("passerProchaineCarte doit réduire le compteur du bouclier")
    void passerProchaineCarteBouclierTest() {
        partie.activerBouclier();

        partie.passerProchaineCarte();
        assertEquals(2, partie.getCompterBouclier());
        assertTrue(partie.isBouclier());

        partie.passerProchaineCarte();
        assertEquals(1, partie.getCompterBouclier());
        assertTrue(partie.isBouclier());

        partie.passerProchaineCarte();

        assertEquals(0, partie.getCompterBouclier());
        assertFalse(partie.isBouclier());
    }

    @Test
    @DisplayName("Vérification complète de tous les champs, getter et setter")
    void testGettersSettersUtilisateur() {

        Utilisateur user = new Utilisateur();

        user.setId(1L);
        user.setPseudo("Gamer77");
        user.setPassword("password123");
        user.setScore(500);
        user.setPieces(150);
        user.setRole("ADMIN");

        assertEquals(1L, user.getId());
        assertEquals("Gamer77", user.getPseudo());
        assertEquals("password123", user.getPassword());
        assertEquals(500, user.getScore());
        assertEquals(150, user.getPieces());
        assertEquals("ADMIN", user.getRole());
    }
}