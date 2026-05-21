package be.helha.poo3.randocard.dao;

import be.helha.poo3.randocard.model.UtilisateurItem;
import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Utilisateur item dao.
 */
public interface UtilisateurItemDAO extends JpaRepository<UtilisateurItem, Integer> {

    /**
     * Find by utilisateur id list.
     *
     * @param utilisateurId the utilisateur id
     * @return the list
     */
// Tous les items d'un utilisateur
    List<UtilisateurItem> findByUtilisateurId(Long utilisateurId);

    /**
     * Find by utilisateur id and nom item optional.
     *
     * @param utilisateurId the utilisateur id
     * @param nomItem       the nom item
     * @return the optional
     */
// Un item précis d'un utilisateur (pour modifier la quantité)
    Optional<UtilisateurItem> findByUtilisateurIdAndNomItem(Long utilisateurId, String nomItem);
}