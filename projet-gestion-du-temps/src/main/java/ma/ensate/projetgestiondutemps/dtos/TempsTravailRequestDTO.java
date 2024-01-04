package ma.ensate.projetgestiondutemps.dtos;


import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensate.projetgestiondutemps.entities.Users;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempsTravailRequestDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;

}
