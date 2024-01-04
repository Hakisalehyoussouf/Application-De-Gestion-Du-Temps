package ma.ensate.projetgestiondutemps.repositories;


import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    List<Users> findAllByRole(Role role);


}
