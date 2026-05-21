package be.helha.poo3.randocard.filter;

import be.helha.poo3.randocard.security.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Jwt authentication filter.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    /**
     * Instantiates a new Jwt authentication filter.
     *
     * @param jwtUtils the jwt utils
     */
    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String jwtToken = jwtUtils.extractJwtFromRequest(request);

        if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
            String username = jwtUtils.getUsernameFromJwtToken(jwtToken);

            List<GrantedAuthority> authorities = jwtUtils.getRolesFromJwtToken(jwtToken).stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());


            if (username != null) {
                // Créer une authentification basée sur le token
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}