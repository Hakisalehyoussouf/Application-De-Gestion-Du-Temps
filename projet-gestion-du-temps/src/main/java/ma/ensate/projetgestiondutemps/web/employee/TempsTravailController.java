package ma.ensate.projetgestiondutemps.web.employee;


import lombok.AllArgsConstructor;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.services.employee.TempsTravailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/worktime")
@CrossOrigin("*")
@AllArgsConstructor
public class TempsTravailController {

    private final TempsTravailService tempsTravailService;
    private final JwtDecoder jwtDecoder;

    private final int PAGE_SIZE = 4;


    @PostMapping
    public TempsTravailResponseDTO startWork(@RequestBody TempsTravailRequestDTO tempsTravailRequestDTO,
                                             @RequestHeader("Authorization") String jwtToken){


            String token = jwtToken.replace("Bearer ", "");
            Jwt jwt = this.jwtDecoder.decode(token);
            String email = jwt.getSubject();


            tempsTravailRequestDTO.setId(null);
            tempsTravailRequestDTO.setDateFin(null);

            TempsTravailResponseDTO tempsTravailResponseDTO = this.tempsTravailService.save(tempsTravailRequestDTO, email);

             return tempsTravailResponseDTO;

    }




    @PutMapping("/{id}")
    public TempsTravailResponseDTO finishWork(@PathVariable Long id,
                                              @RequestBody TempsTravailRequestDTO tempsTravailRequestDTO){

        return  this.tempsTravailService.update(id,tempsTravailRequestDTO);
    }



    @GetMapping
    public List<TempsTravailResponseDTO> getAll(@RequestParam(name = "sort") String sort,
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
        return this.tempsTravailService.allWorkTimes(pageable,email);

    }

    @GetMapping("/today")
    public List<TempsTravailResponseDTO> getToday(@RequestParam(name = "sort") String sort,
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
        return this.tempsTravailService.allTodayWorkTime(pageable,email);

    }


    @GetMapping("/todayworktime")
    public TempsTravailResponseDTO getToday(@RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        String email = jwt.getSubject();


        return this.tempsTravailService.TodayWorkTimeOnly(email);

    }

}
