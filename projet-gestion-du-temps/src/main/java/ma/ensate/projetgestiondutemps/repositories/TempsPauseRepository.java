package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.TempsPause;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TempsPauseRepository extends JpaRepository<TempsPause, Long> {


    @Query("SELECT t FROM TempsPause t WHERE t.user = :user AND t.id = (SELECT MAX(tt.id) FROM TempsPause tt WHERE tt.user = :user)")
    Optional<TempsPause> findLatestByUser(@Param("user") Users user);

    List<TempsPause> findAllByUser(Users user, Pageable pageable);

    List<TempsPause> findAllByUser(Users user);

    List<TempsPause> findByDateDebutBetweenAndUserEquals(Date startDate, Date endDate, Users user);


}
