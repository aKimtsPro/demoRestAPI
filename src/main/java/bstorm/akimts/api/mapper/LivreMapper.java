package bstorm.akimts.api.mapper;

import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.models.entity.Auteur;
import bstorm.akimts.api.models.entity.Livre;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LivreMapper {

    public LivreDTO toDTO(Livre entity){

        if( entity == null )
            return null;

        return LivreDTO.builder()
                .isbn( entity.getIsbn() )
                .titre( entity.getTitre() )
                .prix( entity.getPrix() )
                .auteurs(
                        entity.getAuteurs()
                                .stream()
                                .map(this::toInnerDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private LivreDTO.AuteurDTO toInnerDto(Auteur auteur){
        if(auteur == null)
            return null;

        return LivreDTO.AuteurDTO.builder()
                .id(auteur.getId())
                .nom(auteur.getNom())
                .prenom(auteur.getPrenom())
                .build();
    }

}
