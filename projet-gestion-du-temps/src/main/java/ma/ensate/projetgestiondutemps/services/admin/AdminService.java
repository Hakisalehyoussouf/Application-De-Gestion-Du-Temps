package ma.ensate.projetgestiondutemps.services.admin;

import ma.ensate.projetgestiondutemps.dtos.UsersRequestDTO;
import ma.ensate.projetgestiondutemps.entities.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    Users addEmployee(UsersRequestDTO usersRequestDTO);
    List<Users> getAllUsers();
    Users getUserById(long id);
    void deleteUser(long id);
    Page<Users> getUserByPaginate(int currentPage, int size);

    String getFullName(Long id);

    Long getWorkHours(Long id);

    Long getOverHours(Long id);
}
