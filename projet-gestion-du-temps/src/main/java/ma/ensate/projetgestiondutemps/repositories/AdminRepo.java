package ma.ensate.projetgestiondutemps.repositories;

import ma.ensate.projetgestiondutemps.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdminRepo extends JpaRepository<Users, Long>{

}
