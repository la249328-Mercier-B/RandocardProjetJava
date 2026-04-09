package be.helha.poo3.randocard.dao;

import be.helha.poo3.randocard.model.UtilisateurItem;
import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurItemDAO extends JpaRepository<UtilisateurItem, Integer> {

    // Tous les items d'un utilisateur
    List<UtilisateurItem> findByUtilisateurId(Long utilisateurId);

    // Un item précis d'un utilisateur (pour modifier la quantité)
    Optional<UtilisateurItem> findByUtilisateurIdAndNomItem(Long utilisateurId, String nomItem);
}