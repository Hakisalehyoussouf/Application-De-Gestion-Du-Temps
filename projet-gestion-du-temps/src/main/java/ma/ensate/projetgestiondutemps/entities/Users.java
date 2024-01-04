package ma.ensate.projetgestiondutemps.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ma.ensate.projetgestiondutemps.enums.Role;

import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TempsTravail>  tempsTravail;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TempsPause> tempsPause;


}
