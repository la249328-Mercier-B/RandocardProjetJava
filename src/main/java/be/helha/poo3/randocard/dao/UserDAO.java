package be.helha.poo3.randocard.dao;

import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDAO extends JpaRepository<Utilisateur, Long> {
    /**
     * Find by pseudo optional.
     *
     * @param pseudo the pseudo
     * @return the optional
     */
    Optional<Utilisateur> findByPseudo(String pseudo);

    /**
     * Find 5 meilleurs scores list.
     *
     * @return the list
     */
    @Query("SELECT u FROM Utilisateur u ORDER BY u.score DESC LIMIT 5")
    List<Utilisateur> find5MeilleursScores();
}
