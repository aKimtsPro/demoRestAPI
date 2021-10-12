package bstorm.akimts.api.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LivreDTO {

    private String isbn;
    private String titre;
    private double prix;
    private List<LivreDTO.AuteurDTO> auteurs;

    @Data
    @Builder
    public static class AuteurDTO {
        private long id;
        private String nom;
        private String prenom;
    }

}
