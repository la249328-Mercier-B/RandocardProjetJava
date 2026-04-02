package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.model.Partie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeuController {

    private final Partie partie;

    public JeuController() {
        this.partie = new Partie();
    }

    @GetMapping("/verifierNombre/{grandPetit}")
    public boolean verifierNombre(@PathVariable("grandPetit") boolean bool) {
        boolean verif = partie.verif(bool);
        if (!verif) {
            this.partie.perdreUnCoeur();
        }
        if (this.partie.isPartieEnCours()) {
            partie.passerProchaineCarte();
        }
        return verif;
    }
}
