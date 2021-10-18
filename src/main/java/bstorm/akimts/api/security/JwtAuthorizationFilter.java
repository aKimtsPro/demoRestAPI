package bstorm.akimts.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtAuthorizationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Recupérer le token
        String token = jwtProvider.resolveToken(request);
        // Verifier si le token est valide
        if( token != null && jwtProvider.validate( token ) ){
            // si valid => enregistrer l'utilisateur contenu comme authentifié avec ses roles
            Authentication auth = jwtProvider.getAuth( token );
            SecurityContextHolder.getContext().setAuthentication( auth );
        }

        filterChain.doFilter( request, response );

    }
}
