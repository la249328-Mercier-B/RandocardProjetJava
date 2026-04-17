package be.helha.poo3.randocard.mapper;

import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.dto.UserOut;
import be.helha.poo3.randocard.model.Utilisateur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Utilisateur toEntity(UserIn userIn);
    List<UserOut> toDTO(List<Utilisateur> utilisateurs);
}
