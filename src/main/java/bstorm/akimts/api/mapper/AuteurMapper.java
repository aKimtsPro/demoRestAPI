package bstorm.akimts.api.mapper;

import bstorm.akimts.api.models.dto.AuteurDTO;
import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.models.entity.Livre;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuteurMapper {

    public AuteurDTO toDTO(Auteur entity){

        if( entity == null )
            return null;

        return AuteurDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .livres(
                        entity.getLivres().stream()
                                .map(this::toInnerDTO)
                                .collect(Collectors.toList())
                )
                .build();

    }

    private AuteurDTO.LivreDTO toInnerDTO(Livre entity){

        if( entity == null )
            return null;

        return AuteurDTO.LivreDTO.builder()
                .isbn(entity.getIsbn())
                .titre(entity.getTitre())
                .prix(entity.getPrix())
                .build();

    }

}
