package be.helha.poo3.randocard.service;

import be.helha.poo3.randocard.dao.UserDAO;
import be.helha.poo3.randocard.model.Utilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    /**
     * Instantiates a new Custom user details service.
     *
     * @param userDAO the user dao
     */
    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = userDAO.findByPseudo(username).orElseThrow();

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        return User.builder()
                .username(utilisateur.getPseudo())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRole())
                .build();
    }
}