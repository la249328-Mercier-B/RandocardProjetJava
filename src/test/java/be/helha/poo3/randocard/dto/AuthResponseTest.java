package be.helha.poo3.randocard.dto;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de AuthResponse")
class AuthResponseTest {

    @BeforeAll
    static void demarrageSuiteDeTests() {
        System.out.println("=== Début des tests de AuthResponse ===");
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de AuthResponse ===");
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
    @DisplayName("Le constructeur doit initialiser le token et le message")
    void constructeurTest() {
        AuthResponse response = new AuthResponse("monToken", "Authentification réussie !");

        assertEquals("monToken", response.getToken());
        assertEquals("Authentification réussie !", response.getMessage());
    }

    @Test
    @DisplayName("setToken doit mettre à jour le token")
    void setTokenTest() {
        AuthResponse response = new AuthResponse("ancienToken", "message");

        response.setToken("nouveauToken");
        assertEquals("nouveauToken", response.getToken());
    }

    @Test
    @DisplayName("setMessage doit mettre à jour le message")
    void setMessageTest() {
        AuthResponse response = new AuthResponse("token", "ancien message");

        response.setMessage("nouveau message");
        assertEquals("nouveau message", response.getMessage());
    }
}