package ma.ensate.projetgestiondutemps.dtos;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensate.projetgestiondutemps.entities.Users;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempsPauseRequestDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;

}
