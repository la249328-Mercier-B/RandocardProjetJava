package be.helha.poo3.randocard.dao;

import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByPseudo(String pseudo);
}
