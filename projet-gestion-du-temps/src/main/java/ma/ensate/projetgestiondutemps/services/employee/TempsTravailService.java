package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TempsTravailService {

    TempsTravailResponseDTO save(TempsTravailRequestDTO tempsTravailRequestDTO, String email);

    TempsTravailResponseDTO update(Long id,TempsTravailRequestDTO tempsTravailRequestDTO);
    TempsTravailResponseDTO getWorkTime(Long id);

    boolean verifyStarting(String email);

    List<TempsTravailResponseDTO> allWorkTimes(Pageable pageable, String email);

    List<TempsTravailResponseDTO> allTodayWorkTime(Pageable pageable, String email);


    boolean verifyFinishing(String email);

    TempsTravailResponseDTO TodayWorkTimeOnly(String email);
}
