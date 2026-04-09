package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.dao.UserDAO;
import be.helha.poo3.randocard.factory.ItemRepository;
import be.helha.poo3.randocard.model.Item;
import be.helha.poo3.randocard.model.Partie;
import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JeuController {

    private final Partie partie;
    private final UserDAO userDAO;
    private final ItemRepository itemRepository;

    public JeuController(UserDAO userDAO, ItemRepository itemRepository) {
        this.partie = new Partie();
        this.userDAO = userDAO;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/verifierNombre/{grandPetit}")
    public boolean verifierNombre(@PathVariable("grandPetit") boolean bool,
                                  Authentication authentication) {
        if (partie.isPartieEnCours()) {
            String pseudo = authentication.getName();

            Utilisateur utilisateurConnecte = userDAO.findByPseudo(pseudo).orElseThrow();

            boolean verif = partie.verif(bool);

            if (!verif) {
                partie.perdreUnCoeur();
                System.out.println("Mauvaise réponse !");
            } else {
                partie.setScorePartie(partie.getScorePartie() + 1);
                System.out.println("Bonne réponse ! Votre score passe à: " + partie.getScorePartie());
            }


            if (partie.isPartieEnCours()) {
                partie.passerProchaineCarte();
            } else {
                if (partie.getScorePartie() > utilisateurConnecte.getScore()) {
                    utilisateurConnecte.setScore(partie.getScorePartie());
                }
                utilisateurConnecte.setPieces(
                        utilisateurConnecte.getPieces() + partie.getScorePartie()
                );
                userDAO.save(utilisateurConnecte);
            }

            return verif;
        }
        return false;
    }

    @GetMapping("/items")
    public List<Item> getItems() throws Exception {
        return itemRepository.findAll();
    }
}