package ma.ensate.projetgestiondutemps.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Humeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String humeur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();


}
