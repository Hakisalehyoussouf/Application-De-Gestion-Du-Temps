package ma.ensate.projetgestiondutemps.mappers;


import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.entities.TempsPause;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TempsTravailMapper {

    TempsTravailResponseDTO tempsTravailToTempsTravailResponseDTO(TempsTravail tempsTravail);
    TempsTravail tempsTravailRequestDTOToTempsTravail(TempsTravailRequestDTO tempsTravailRequestDTO);
}
