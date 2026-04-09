package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.dao.UserDAO;
import be.helha.poo3.randocard.dao.UtilisateurItemDAO;
import be.helha.poo3.randocard.factory.ItemRepository;
import be.helha.poo3.randocard.model.Item;
import be.helha.poo3.randocard.model.Partie;
import be.helha.poo3.randocard.model.Utilisateur;
import be.helha.poo3.randocard.model.UtilisateurItem;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class JeuController {

    private final Partie partie;
    private final UserDAO userDAO;
    private final UtilisateurItemDAO utilisateurItemDAO;
    private final ItemRepository itemRepository;

    public JeuController(UserDAO userDAO, UtilisateurItemDAO utilisateurItemDAO, ItemRepository itemRepository) {
        this.utilisateurItemDAO = utilisateurItemDAO;
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

    @GetMapping("/mesItems")
    public List<UtilisateurItem> getMesItems(Authentication authentication) {
        String pseudo = authentication.getName();
        Utilisateur utilisateur = userDAO.findByPseudo(pseudo).orElseThrow();
        return utilisateurItemDAO.findByUtilisateurId(utilisateur.getId());
    }

    @PostMapping("/acheter/{nomItem}")
    public ResponseEntity<String> acheterItem(@PathVariable String nomItem,
                              Authentication authentication) throws Exception {

        String pseudo = authentication.getName();
        Utilisateur utilisateur = userDAO.findByPseudo(pseudo).orElseThrow();

        // Vérifier que l'item existe dans MongoDB
        Optional<Item> itemOpt = itemRepository.findByNom(nomItem);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Item inconnu : " + nomItem);
        }
        Item item = itemOpt.get();

        // Vérifier que l'utilisateur a assez de pièces
        if (utilisateur.getPieces() < item.getCout()) {
            return ResponseEntity.badRequest().body("Pas assez de pièces !");
        }

        // Débiter les pièces
        utilisateur.setPieces(utilisateur.getPieces() - item.getCout());
        userDAO.save(utilisateur);

        // Ajouter ou incrémenter la quantité
        Optional<UtilisateurItem> existant = utilisateurItemDAO
                .findByUtilisateurIdAndNomItem(utilisateur.getId(), nomItem);

        if (existant.isPresent()) {
            existant.get().setQuantite(existant.get().getQuantite() + 1);
            utilisateurItemDAO.save(existant.get());
        } else {
            utilisateurItemDAO.save(new UtilisateurItem(0, utilisateur, nomItem, 1));
        }

        return ResponseEntity.ok("Item " + nomItem + " acheté !");
    }
}