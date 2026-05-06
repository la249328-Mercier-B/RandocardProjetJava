package be.helha.poo3.randocard.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de UserIn")
class UserInTest {

    private static Validator validator;

    @BeforeAll
    static void démarrageSuiteDeTests() {
        System.out.println("=== Début des tests de UserIn ===");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void finSuiteDeTests() {
        System.out.println("=== Fin des tests de UserIn ===");
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
    @DisplayName("Le record doit initialiser le pseudo et le password")
    void constructeurTest() {
        UserIn userIn = new UserIn("test", "motdepasse");

        assertEquals("test", userIn.pseudo());
        assertEquals("motdepasse", userIn.password());
    }

    @Test
    @DisplayName("Un pseudo vide doit déclencher une violation de contrainte")
    void pseudoVideTest() {
        UserIn userIn = new UserIn("", "motdepasse");
        List<ConstraintViolation<UserIn>> violations = new ArrayList<>(validator.validate(userIn));

        assertFalse(violations.isEmpty());
        assertEquals("Le pseudo est obligatoire", violations.get(0).getMessage());
    }

    @Test
    @DisplayName("Un password vide doit déclencher une violation de contrainte")
    void passwordVideTest() {
        UserIn userIn = new UserIn("test", "");
        List<ConstraintViolation<UserIn>> violations = new ArrayList<>(validator.validate(userIn));

        assertFalse(violations.isEmpty());
        assertEquals("Le mot de passe est obligatoire", violations.get(0).getMessage());
    }

    @Test
    @DisplayName("Des valeurs valides ne doivent pas déclencher de violation")
    void donnéesValidesTest() {
        UserIn userIn = new UserIn("test", "motdepasse");
        List<ConstraintViolation<UserIn>> violations = new ArrayList<>(validator.validate(userIn));

        assertTrue(violations.isEmpty());
    }
}