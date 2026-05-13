package be.helha.poo3.randocard.controller;

import be.helha.poo3.randocard.dao.UserDAO;
import be.helha.poo3.randocard.dao.UtilisateurItemDAO;
import be.helha.poo3.randocard.dto.UserOut;
import be.helha.poo3.randocard.factory.ItemFactory;
import be.helha.poo3.randocard.factory.ItemRepository;
import be.helha.poo3.randocard.mapper.UserMapper;
import be.helha.poo3.randocard.model.Item;
import be.helha.poo3.randocard.model.Partie;
import be.helha.poo3.randocard.model.Utilisateur;
import be.helha.poo3.randocard.model.UtilisateurItem;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class JeuController {

    private final Partie partie;
    private final UserDAO userDAO;
    private final UtilisateurItemDAO utilisateurItemDAO;
    private final ItemRepository itemRepository;
    private final UserMapper userMapper;

    public JeuController(UserDAO userDAO, UtilisateurItemDAO utilisateurItemDAO, ItemRepository itemRepository,  UserMapper userMapper) {
        this.utilisateurItemDAO = utilisateurItemDAO;
        this.partie = new Partie();
        this.userDAO = userDAO;
        this.itemRepository = itemRepository;
        this.userMapper= userMapper;
    }

    @GetMapping("/lancerPartie")
    public void  lancerPartie() {
        this.partie.lancerPartie();
        System.out.println("Partie lancée");
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

    @PostMapping("/acheterItem/{nomItem}")
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

    @PostMapping("utiliserItem/{nomItem}")
    public ResponseEntity<String> utiliserItem(@PathVariable String nomItem,
                                               Authentication authentication) throws Exception {

        String pseudo = authentication.getName();
        Utilisateur utilisateur = userDAO.findByPseudo(pseudo).orElseThrow();

        Optional<UtilisateurItem> existant = utilisateurItemDAO
                .findByUtilisateurIdAndNomItem(utilisateur.getId(), nomItem);

        if (existant.isEmpty() || existant.get().getQuantite() <= 0) {
            return ResponseEntity.badRequest().body("Tu ne possèdes pas cet item !");
        }

        Item itemStats = itemRepository.findByNom(nomItem)
                .orElseThrow(() -> new Exception("Données de l'item introuvables dans le catalogue"));

        itemStats.utiliser(partie);

        UtilisateurItem utilisateurItem = existant.get();
        utilisateurItem.setQuantite(utilisateurItem.getQuantite() - 1);

        if (utilisateurItem.getQuantite() == 0) {
            utilisateurItemDAO.delete(utilisateurItem); // Optionnel : supprimer si quantité 0
        } else {
            utilisateurItemDAO.save(utilisateurItem);
        }

        return ResponseEntity.ok("Item " + nomItem + " utilisé !");
    }

    @GetMapping("/recupNomUtilisateur")
    public ResponseEntity<String> recupNomUtilisateur(Authentication authentication) throws Exception {
        String pseudo = authentication.getName();
        return ResponseEntity.ok(pseudo);
    }

    @GetMapping("/recupPieceUtilisateur")
    public  ResponseEntity<Integer> recupPieceUtilisateur(Authentication authentication) throws Exception {
        String pseudo = authentication.getName();
        Utilisateur utilisateur = userDAO.findByPseudo(pseudo).orElseThrow();

        return ResponseEntity.ok(utilisateur.getPieces());
    }

    @GetMapping("/recupScoreUtilisateur")
    public  ResponseEntity<Integer> recupScoreUtilisateur(Authentication authentication) throws Exception {
        String pseudo = authentication.getName();
        Utilisateur utilisateur = userDAO.findByPseudo(pseudo).orElseThrow();

        return ResponseEntity.ok(utilisateur.getScore());
    }

    @GetMapping("/recup5MeilleursScore")
    public ResponseEntity<List<UserOut>> getMeilleurs() {
        List<Utilisateur> utilisateurs = userDAO.find5MeilleursScores();
        List<UserOut> usersOut = userMapper.toDTO(utilisateurs);

        return ResponseEntity.ok(usersOut);
    }

    @GetMapping("/recupScorePartie")
    public ResponseEntity<Integer> getScorePartie() {
        int scorePartie = partie.getScorePartie();

        return ResponseEntity.ok(scorePartie);
    }

    @GetMapping("/recupNbVisible")
    public ResponseEntity<Integer> getNbVisible() {
        int nbVisible = partie.getNbVisible();

        return ResponseEntity.ok(nbVisible);
    }

    @GetMapping("/recupNbCoeur")
    public ResponseEntity<Integer> getNbCoeur() {
        int nbCoeurs = partie.getNbCoeurs();

        return ResponseEntity.ok(nbCoeurs);
    }

    @GetMapping("/recupNbCoeursMax")
    public ResponseEntity<Integer> getNbCoeursMax() {
        int nbCoeursMax = partie.getNbCoeursMax();

        return ResponseEntity.ok(nbCoeursMax);
    }
}