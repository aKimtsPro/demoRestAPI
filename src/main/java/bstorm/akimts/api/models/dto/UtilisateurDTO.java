package bstorm.akimts.api.models.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;

@Data
@Builder
public class UtilisateurDTO {

    private long id;
    private String username;

    private List<String> roles;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
