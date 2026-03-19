package be.helha.poo3.randocard.mapper;

import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.model.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Utilisateur toEntity(UserIn userIn);
}
