package bstorm.akimts.api.service;

import bstorm.akimts.api.exceptions.ElementNotFoundException;
import bstorm.akimts.api.mapper.AuteurMapper;
import bstorm.akimts.api.models.dto.AuteurDTO;
import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.repository.AuteurRepository;
import org.springframework.stereotype.Service;

@Service
public class AuteurServiceImpl implements AuteurService {

    private final AuteurMapper mapper;
    private final AuteurRepository repository;

    public AuteurServiceImpl(AuteurMapper mapper, AuteurRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public AuteurDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }
}
