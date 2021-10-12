package bstorm.akimts.api.service;

import bstorm.akimts.api.exceptions.ElementNotFoundException;
import bstorm.akimts.api.mapper.LivreMapper;
import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.models.entity.Livre;
import bstorm.akimts.api.models.form.LivreForm;
import bstorm.akimts.api.repository.AuteurRepository;
import bstorm.akimts.api.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivreServiceImpl implements LivreService{

    private final LivreMapper mapper;
    private final LivreRepository repository;
    private final AuteurRepository auteurRepository;

    public LivreServiceImpl(LivreMapper mapper, LivreRepository repository, AuteurRepository auteurRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public List<LivreDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LivreDTO getOne(String isbn) {
        return repository.findById(isbn)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public LivreDTO insert(LivreForm form) {

        Livre toInsert = mapper.formToEntity(form);
        Set<Auteur> auteurs = form.getAuteurIds()
                .stream()
                .map(id -> auteurRepository.findById(id)
                                .orElseThrow(ElementNotFoundException::new))
                .collect(Collectors.toSet());
        toInsert.setAuteurs( auteurs );

        toInsert = repository.save(toInsert);

        return mapper.toDTO(toInsert);
    }
}
