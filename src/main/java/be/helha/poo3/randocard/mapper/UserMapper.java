package be.helha.poo3.randocard.mapper;

import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.dto.UserOut;
import be.helha.poo3.randocard.model.Utilisateur;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface User mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * To entity utilisateur.
     *
     * @param userIn the user in
     * @return the utilisateur
     */
    Utilisateur toEntity(UserIn userIn);

    /**
     * To dto list.
     *
     * @param utilisateurs the utilisateurs
     * @return the list
     */
    List<UserOut> toDTO(List<Utilisateur> utilisateurs);
}
