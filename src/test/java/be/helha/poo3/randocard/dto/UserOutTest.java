package be.helha.poo3.randocard.dto;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserOutTest {

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
    @DisplayName("Le record doit initialiser le pseudo et le score")
    void constructeurTest() {
        UserOut userOut = new UserOut("test", 100);

        assertEquals("test", userOut.pseudo());
        assertEquals(100, userOut.score());
    }
}
