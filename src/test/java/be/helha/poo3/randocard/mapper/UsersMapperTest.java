package be.helha.poo3.randocard.mapper;

import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.dto.UserOut;
import be.helha.poo3.randocard.model.Utilisateur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    @DisplayName("Devrait mapper UserIn vers Utilisateur avec succès")
    void userInToEntityTest() {
        UserIn userIn = new UserIn("Test", "CaputDraconis");

        Utilisateur result = mapper.toEntity(userIn);

        assertNotNull(result, "L'entité résultante ne doit pas être null");
        assertEquals("Test", result.getPseudo());
        assertEquals("CaputDraconis", result.getPassword());
    }

    @Test
    @DisplayName("Devrait retourner null si le DTO en entrée est null")
    void toEntityNullInput() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Devrait mapper une liste d'Utilisateurs vers une liste de UserOut")
    void utilisateursListToDtoTest() {
        Utilisateur u1 = new Utilisateur();
        u1.setPseudo("Alessio");
        Utilisateur u2 = new Utilisateur();
        u2.setPseudo("Brillando");
        List<Utilisateur> list = List.of(u1, u2);

        List<UserOut> result = mapper.toDTO(list);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alessio", result.get(0).pseudo());
    }

    @Test
    @DisplayName("Devrait retourner null si la liste entrée est nulle")
    void toDTO_ShouldMapList() {
        List<Utilisateur> list = null;
        assertNull(mapper.toDTO(list));
    }
}