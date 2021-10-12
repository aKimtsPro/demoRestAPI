package bstorm.akimts.api.utils;

import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.models.entity.Livre;
import bstorm.akimts.api.repository.AuteurRepository;
import bstorm.akimts.api.repository.LivreRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseFiller implements InitializingBean {

    private final LivreRepository lRep;
    private final AuteurRepository aRep;

    public DatabaseFiller(LivreRepository lRep, AuteurRepository aRep) {
        this.lRep = lRep;
        this.aRep = aRep;
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


    }
}
