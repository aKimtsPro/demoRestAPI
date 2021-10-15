package bstorm.akimts.api.service;

import bstorm.akimts.api.models.entity.Utilisateur;
import bstorm.akimts.api.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UserDetailsService {

    private final UtilisateurRepository repository;

    public UtilisateurServiceImpl(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername( username )
                .orElseThrow( () -> new UsernameNotFoundException("l'utilisateur avec le username donné n'existe pas") );
    }
}
