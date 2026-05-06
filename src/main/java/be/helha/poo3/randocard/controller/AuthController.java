package be.helha.poo3.randocard.controller;


import be.helha.poo3.randocard.connexion.ConnexionMongoDb;
import be.helha.poo3.randocard.dto.AuthResponse;
import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserIn userIn) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userIn.pseudo(),
                            userIn.password()
                    )
            );

            // Stocker l'authentification dans le SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Génération du token JWT
            String jwtToken = jwtUtils.generateToken(authentication);

            ConnexionMongoDb.getDatabase();

            return ResponseEntity.ok(new AuthResponse(jwtToken, "Authentification réussie !"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Échec de l'authentification : " + e.getMessage());
        }
    }
}