package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.dtos.HumeurRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.HumeurResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.entities.Humeur;

import java.util.List;

public interface HumeurService {


    HumeurResponseDTO save(HumeurRequestDTO humeurRequestDTO, String email);
    List<HumeurResponseDTO> getAllTodayFeedBack();
    public boolean verifyFinishing(String email);

    List<Humeur> getAllHumeurs();

}
