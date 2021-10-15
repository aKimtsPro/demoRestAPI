package bstorm.akimts.api.utils;

import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.models.entity.Livre;
import bstorm.akimts.api.models.entity.Utilisateur;
import bstorm.akimts.api.repository.AuteurRepository;
import bstorm.akimts.api.repository.LivreRepository;
import bstorm.akimts.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseFiller implements InitializingBean {

    private final LivreRepository lRep;
    private final AuteurRepository aRep;
    private final UtilisateurRepository uRep;
    private final PasswordEncoder encoder;

    public DatabaseFiller(LivreRepository lRep, AuteurRepository aRep, UtilisateurRepository uRep, PasswordEncoder encoder) {
        this.lRep = lRep;
        this.aRep = aRep;
        this.uRep = uRep;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Auteur a = new Auteur();
        a.setNom("dubois");
        a.setPrenom("pol");

        a = aRep.save(a);

        Livre l1 = new Livre("ISBN1", "titre1", 1, Set.of(a));
        Livre l2 = new Livre("ISBN2", "titre2", 2, Set.of(a));

        lRep.saveAll(List.of(l1,l2));


        Utilisateur user = new Utilisateur();

        user.setUsername("user");
        user.setPassword( encoder.encode("pass") );

        user.setRoles( List.of("USER") );

        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        Utilisateur admin = new Utilisateur();

        admin.setUsername("admin");
        admin.setPassword( encoder.encode("pass") );

        admin.setRoles( List.of("ADMIN","USER") );

        admin.setEnabled(true);
        admin.setAccountNonLocked(true);
        admin.setAccountNonExpired(true);
        admin.setCredentialsNonExpired(true);

        uRep.save(user);
        uRep.save(admin);

    }
}
