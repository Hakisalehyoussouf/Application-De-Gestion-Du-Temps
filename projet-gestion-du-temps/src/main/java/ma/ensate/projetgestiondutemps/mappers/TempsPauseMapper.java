package ma.ensate.projetgestiondutemps.mappers;

import ma.ensate.projetgestiondutemps.dtos.HumeurRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.HumeurResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.entities.Humeur;
import ma.ensate.projetgestiondutemps.entities.TempsPause;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TempsPauseMapper {

    TempsPauseResponseDTO tempsPauseToTempsPauseResponseDTO(TempsPause tempsPause);
    TempsPause tempsPauseRequestDTOToTempsPause(TempsPauseRequestDTO tempsPauseRequestDTO);
}
