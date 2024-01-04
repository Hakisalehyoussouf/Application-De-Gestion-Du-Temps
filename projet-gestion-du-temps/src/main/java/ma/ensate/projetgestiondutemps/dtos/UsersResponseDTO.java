package ma.ensate.projetgestiondutemps.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensate.projetgestiondutemps.enums.Role;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;


    private List<TempsTravailResponseDTO> tempsTravailDTOS;

    private List<TempsPauseResponseDTO> tempsPauseDTOS;
}
