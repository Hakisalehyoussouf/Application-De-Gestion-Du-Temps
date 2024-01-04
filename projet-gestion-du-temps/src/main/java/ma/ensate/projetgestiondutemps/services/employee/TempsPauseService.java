package ma.ensate.projetgestiondutemps.services.employee;

import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TempsPauseService {

    TempsPauseResponseDTO save(TempsPauseRequestDTO tempsPauseRequestDTO, String email);

    TempsPauseResponseDTO update(Long id,TempsPauseRequestDTO tempsPauseRequestDTO);
    TempsPauseResponseDTO getBreakTime(Long id);

    List<TempsPauseResponseDTO> allBreakTime(Pageable pageable, String email);

    List<TempsPauseResponseDTO> allTodayBreakTime(Pageable pageable, String email);
    TempsPauseResponseDTO TodayBreakTimeOnly(String email);

    boolean verifyStarting(String email);
    public boolean verifyFinishing(String email);

}
