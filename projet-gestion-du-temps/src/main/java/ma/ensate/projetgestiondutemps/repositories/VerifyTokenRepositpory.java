package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.Humeur;
import ma.ensate.projetgestiondutemps.entities.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyTokenRepositpory extends JpaRepository<VerifyToken, Long> {

    VerifyToken findByToken(String token);

    void  deleteByToken(String token);
}
