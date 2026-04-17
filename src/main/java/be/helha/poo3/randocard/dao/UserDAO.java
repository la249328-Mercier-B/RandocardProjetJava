package be.helha.poo3.randocard.dao;

import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByPseudo(String pseudo);

    @Query("SELECT u FROM Utilisateur u ORDER BY u.score DESC LIMIT 5")
    List<Utilisateur> find5MeilleursScores();
}
