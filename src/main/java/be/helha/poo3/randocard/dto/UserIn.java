package be.helha.poo3.randocard.dto;

import jakarta.validation.constraints.NotBlank;

public record UserIn(
        @NotBlank(message = "Le pseudo est obligatoire")
        String pseudo,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String password
) {
}
