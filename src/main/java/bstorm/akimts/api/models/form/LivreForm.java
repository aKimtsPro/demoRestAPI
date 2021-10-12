package bstorm.akimts.api.models.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Validated
public class LivreForm {

    @NotBlank
    @Length(min = 13, max = 13)
    private String isbn;
    @NotBlank
    private String titre;
    @Min(0)
    private double prix;
    @NotEmpty
    private List<Long> auteurIds;

}
