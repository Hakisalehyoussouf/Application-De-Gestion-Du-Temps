package ma.ensate.projetgestiondutemps.mappers;

import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.UsersRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.UsersResponseDTO;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersResponseDTO usersToUsersResponseDTO(Users user);
    Users usersRequestDTOToUser(UsersRequestDTO usersRequestDTO);
}
