package be.helha.poo3.randocard.dto;

public class LoginRequest {
    private String pseudo;
    private String password;

    public LoginRequest(String pseudoExistant, String passwordCorrect) {
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }
}
