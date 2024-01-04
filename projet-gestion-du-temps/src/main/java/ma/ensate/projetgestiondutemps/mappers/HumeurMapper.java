package ma.ensate.projetgestiondutemps.mappers;


import ma.ensate.projetgestiondutemps.dtos.HumeurRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.HumeurResponseDTO;
import ma.ensate.projetgestiondutemps.entities.Humeur;
import org.mapstruct.Mapper;



@Mapper(componentModel="spring")
public interface HumeurMapper {

    HumeurResponseDTO humeurToHumeurResponseDTO(Humeur humeur);
    Humeur humeurRequestDTOToHumeur(HumeurRequestDTO humeurRequestDTO);

}
