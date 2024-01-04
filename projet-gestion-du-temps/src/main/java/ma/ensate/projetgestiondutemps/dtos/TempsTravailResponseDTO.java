package ma.ensate.projetgestiondutemps.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempsTravailResponseDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;

    
}
