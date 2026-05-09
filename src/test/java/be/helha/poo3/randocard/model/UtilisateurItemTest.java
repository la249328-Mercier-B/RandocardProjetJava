package be.helha.poo3.randocard.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires de UtilisateurItem")
class UtilisateurItemTest {

    private Utilisateur utilisateur;
    private UtilisateurItem utilisateurItem;

    @BeforeAll
    static void demarrageSuiteDeTests() {
        System.out.println("=== Début des tests de UtilisateurItem ===");
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de UtilisateurItem ===");
    }

    @BeforeEach
    void avantChaqueTest(TestInfo testInfo) {
        System.out.println("-- Début du test : " + testInfo.getDisplayName());

        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPseudo("Aventurier");

        utilisateurItem = new UtilisateurItem();
    }

    @AfterEach
    void apresChaqueTest(TestInfo testInfo) {
        System.out.println("-- Fin du test : " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test complet des getters et setters")
    void testGettersSetters() {

        int id = 5;
        String nomItem = "EpeeDeFeu";
        int quantite = 3;

        utilisateurItem.setId(id);
        utilisateurItem.setNomItem(nomItem);
        utilisateurItem.setQuantite(quantite);
        utilisateurItem.setUtilisateur(utilisateur);

        assertEquals(5, utilisateurItem.getId());
        assertEquals("EpeeDeFeu", utilisateurItem.getNomItem());
        assertEquals(3, utilisateurItem.getQuantite());
        assertEquals("Aventurier", utilisateurItem.getUtilisateur().getPseudo());

    }

    @Test
    @DisplayName("Le constructeur AllArgsConstructor doit initialiser tous les champs correctement")
    void testConstructeurComplet() {

        UtilisateurItem complet = new UtilisateurItem(10, utilisateur, "Bouclier", 1);

        assertEquals(10, complet.getId());
        assertEquals(utilisateur, complet.getUtilisateur());
        assertEquals("Bouclier", complet.getNomItem());
        assertEquals(1, complet.getQuantite());

    }
}