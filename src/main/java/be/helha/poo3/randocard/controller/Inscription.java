package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.dao.UserDAO;
import be.helha.poo3.randocard.dto.UserIn;
import be.helha.poo3.randocard.mapper.UserMapper;
import be.helha.poo3.randocard.model.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
public class Inscription {

    private final UserMapper userMapper;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public Inscription(UserMapper userMapper, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/public/inscription")
    public ResponseEntity<?> AjouterUser(@Valid @RequestBody UserIn userIn, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        if (userDAO.findByPseudo(userIn.pseudo()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erreur : Le pseudo " + userIn.pseudo() + " est déjà utilisé");
        }

        Utilisateur utilisateur = userMapper.toEntity(userIn);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        userDAO.save(utilisateur);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(utilisateur.getId())
                .toUri();

        return ResponseEntity.created(location).body(utilisateur);
    }
}
