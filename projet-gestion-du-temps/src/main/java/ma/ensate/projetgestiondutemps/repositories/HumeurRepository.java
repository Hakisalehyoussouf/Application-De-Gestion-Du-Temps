package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.Humeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HumeurRepository extends JpaRepository<Humeur, Long> {

    List<Humeur> findAllByDate(Date date);


    List<Humeur> findAllByOrderByDateDesc();

    long count();
}
