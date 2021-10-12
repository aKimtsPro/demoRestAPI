package bstorm.akimts.api.service;

import bstorm.akimts.api.mapper.LivreMapper;
import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivreServiceImpl implements LivreService{

    private final LivreMapper mapper;
    private final LivreRepository repository;

    public LivreServiceImpl(LivreMapper mapper, LivreRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<LivreDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
