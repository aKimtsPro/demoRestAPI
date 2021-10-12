package bstorm.akimts.api.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuteurDTO {

    private long id;
    private String nom;
    private String prenom;
    private List<AuteurDTO.LivreDTO> livres;

    @Data
    @Builder
    public static class LivreDTO{

        private String isbn;
        private String titre;
        private double prix;

    }

}
