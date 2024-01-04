package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TempsTravailRepository extends JpaRepository<TempsTravail, Long> {

    @Query("SELECT t FROM TempsTravail t WHERE t.user = :user AND t.id = (SELECT MAX(tt.id) FROM TempsTravail tt WHERE tt.user = :user)")
    Optional<TempsTravail> findLatestByUser(@Param("user") Users user);


    List<TempsTravail> findAllByUser(Users user, Pageable pageable);

    List<TempsTravail> findAllByUser(Users user);

    List<TempsTravail> findByDateDebutBetweenAndUserEquals(Date startDate, Date endDate,Users user);


}
