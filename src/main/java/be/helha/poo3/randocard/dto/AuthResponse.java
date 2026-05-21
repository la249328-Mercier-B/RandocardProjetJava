package be.helha.poo3.randocard.dto;

/**
 * The type Auth response.
 */
public class AuthResponse {
    private String token;
    private String message;

    /**
     * Instantiates a new Auth response.
     *
     * @param token   the token
     * @param message the message
     */
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
// Getters et setters
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
