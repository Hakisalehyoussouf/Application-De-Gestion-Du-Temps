package ma.ensate.projetgestiondutemps.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempsTravail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;


    @JsonIgnore
    @ManyToOne
    private Users user;
}
