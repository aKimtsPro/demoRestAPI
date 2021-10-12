package bstorm.akimts.api.service;

import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.models.form.LivreForm;
import bstorm.akimts.api.models.form.LivreUpdateForm;

import java.util.List;

public interface LivreService {

    List<LivreDTO> getAll();
    LivreDTO getOne(String isbn);
    LivreDTO insert(LivreForm form);
    LivreDTO delete(String isbn);
    LivreDTO update(String isbn, LivreUpdateForm form);

}
