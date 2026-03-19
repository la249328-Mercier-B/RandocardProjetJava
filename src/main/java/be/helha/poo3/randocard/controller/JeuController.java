package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.model.Carte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeuController {

    private final Carte carte;

    public JeuController() {
        this.carte=new Carte();
    }

    @GetMapping("/public/verifierNombre/{grandPetit}")
    public boolean verifierNombre(@PathVariable("grandPetit") Boolean bool){

        Boolean verif=carte.verif(bool);
        return verif;
    }


    @GetMapping("/public/changerNombres")
    public void changerNombres(){
        carte.passerProchaineCarte();
    }

}
