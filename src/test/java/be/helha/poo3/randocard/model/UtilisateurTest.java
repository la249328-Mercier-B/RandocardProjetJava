package be.helha.poo3.randocard.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de l'entité Utilisateur")
class UtilisateurTest {

    @BeforeAll
    static void demarrageSuiteDeTests() {
        System.out.println("=== Début des tests de Utilisateur ===");
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de Utilisateur ===");
    }

    @BeforeEach
    void avantChaqueTest(TestInfo testInfo) {
        System.out.println("-- Début du test : " + testInfo.getDisplayName());
    }

    @AfterEach
    void apresChaqueTest(TestInfo testInfo) {
        System.out.println("-- Fin du test : " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Un nouvel utilisateur doit avoir le rôle 'USER' par défaut")
    void roleParDefautTest() {
        Utilisateur utilisateur = new Utilisateur();

        assertEquals("USER", utilisateur.getRole());
    }

    @Test
    @DisplayName("Les scores et pièces doivent être initialisés à 0")
    void valeursInitialesTest() {
        Utilisateur utilisateur = new Utilisateur();

        assertEquals(0, utilisateur.getScore());
        assertEquals(0, utilisateur.getPieces());
    }

    @Test
    @DisplayName("Test de l'intégralité des Getters et Setters (Lombok)")
    void testCompletGettersSetters() {
        Utilisateur utilisateur = new Utilisateur();

        Long id = 10L;
        String pseudo = "PlayerOne";
        String pass = "secret123";
        int score = 500;
        int pieces = 50;
        String role = "ADMIN";

        utilisateur.setId(id);
        utilisateur.setPseudo(pseudo);
        utilisateur.setPassword(pass);
        utilisateur.setScore(score);
        utilisateur.setPieces(pieces);
        utilisateur.setRole(role);

        assertEquals(id, utilisateur.getId());
        assertEquals(pseudo, utilisateur.getPseudo());
        assertEquals(pass, utilisateur.getPassword());
        assertEquals(score, utilisateur.getScore());
        assertEquals(pieces, utilisateur.getPieces());
        assertEquals(role, utilisateur.getRole());
    }

    @Test
    @DisplayName("Le constructeur AllArgsConstructor doit permettre de modifier le rôle")
    void allArgsConstructorTest() {
        Utilisateur admin = new Utilisateur(1L, "Admin", "root", 0, 0, "ADMIN");

        assertEquals(1L, admin.getId());
        assertEquals("Admin", admin.getPseudo());
        assertEquals("root", admin.getPassword());
        assertEquals(0, admin.getScore());
        assertEquals(0, admin.getPieces());
        assertEquals("ADMIN", admin.getRole());
    }
}