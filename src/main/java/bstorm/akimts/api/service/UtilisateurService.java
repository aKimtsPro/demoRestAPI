package bstorm.akimts.api.service;

import bstorm.akimts.api.models.dto.UtilisateurDTO;
import bstorm.akimts.api.models.entity.Utilisateur;
import bstorm.akimts.api.models.form.UtilisateurForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilisateurService extends UserDetailsService {

    UtilisateurDTO insert(UtilisateurForm form);
    UtilisateurDTO update(long id, UtilisateurForm form);
    List<UtilisateurDTO> getAll();

}
