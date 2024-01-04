package ma.ensate.projetgestiondutemps.dtos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensate.projetgestiondutemps.entities.TempsPause;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    private String nom;
    private String prenom;
    private String email;
    private String password;

}
