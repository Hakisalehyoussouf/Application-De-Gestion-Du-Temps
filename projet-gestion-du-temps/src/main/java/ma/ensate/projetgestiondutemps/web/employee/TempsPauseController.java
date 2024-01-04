package ma.ensate.projetgestiondutemps.web.employee;


import lombok.AllArgsConstructor;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.services.employee.TempsPauseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee/breaktime")
@CrossOrigin("*")
@AllArgsConstructor
public class TempsPauseController {

    private final TempsPauseService tempsPauseService;
    private final JwtDecoder jwtDecoder;
    private final int PAGE_SIZE = 4;


    @PostMapping()
    public TempsPauseResponseDTO startBreak(@RequestBody TempsPauseRequestDTO tempsPauseRequestDTO,
                                            @RequestHeader("Authorization") String jwtToken){


        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        tempsPauseRequestDTO.setId(null);
        tempsPauseRequestDTO.setDateFin(null);


        return this.tempsPauseService.save(tempsPauseRequestDTO, email);

    }



    @PutMapping ("/{id}")
    public TempsPauseResponseDTO finishBreack(@RequestBody TempsPauseRequestDTO tempsPauseRequestDTO,
                                              @PathVariable Long id){


        return this.tempsPauseService.update(id,tempsPauseRequestDTO);

    }


    @GetMapping
    public List<TempsPauseResponseDTO> getAll(@RequestParam(name = "sort") String sort,
                                                @RequestParam(name = "order") String order,
                                                @RequestParam(name = "page") int page,
                                                @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        String email = jwt.getSubject();



        Sort.Direction direction = Sort.Direction.ASC;
        if(order.equalsIgnoreCase("desc")){
            direction = Sort.Direction.DESC;
        }

        Sort sortObj = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, this.PAGE_SIZE ,sortObj);
        return this.tempsPauseService.allBreakTime(pageable,email);

    }


    @GetMapping("/today")
    public List<TempsPauseResponseDTO> getToday(@RequestParam(name = "sort") String sort,
                                                  @RequestParam(name = "order") String order,
                                                  @RequestParam(name = "page") int page,
                                                  @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        String email = jwt.getSubject();



        Sort.Direction direction = Sort.Direction.ASC;
        if(order.equalsIgnoreCase("desc")){
            direction = Sort.Direction.DESC;
        }

        Sort sortObj = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, this.PAGE_SIZE ,sortObj);
        return this.tempsPauseService.allTodayBreakTime(pageable,email);

    }


    @GetMapping("/todaybreaktime")
    public TempsPauseResponseDTO getToday(@RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        String email = jwt.getSubject();


        return this.tempsPauseService.TodayBreakTimeOnly(email);


    }






}
