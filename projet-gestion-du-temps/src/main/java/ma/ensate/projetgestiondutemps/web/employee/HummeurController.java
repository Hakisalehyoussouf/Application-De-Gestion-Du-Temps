package ma.ensate.projetgestiondutemps.web.employee;


import lombok.AllArgsConstructor;
import ma.ensate.projetgestiondutemps.dtos.HumeurRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.HumeurResponseDTO;
import ma.ensate.projetgestiondutemps.services.employee.HumeurService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/humeur")
@AllArgsConstructor
@CrossOrigin("*")
public class HummeurController {


    private final HumeurService humeurService;
    private final JwtDecoder jwtDecoder;



    @PostMapping
    public HumeurResponseDTO addNewHumeur(@RequestBody HumeurRequestDTO humeurRequestDTO,
                                          @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        return this.humeurService.save(humeurRequestDTO, email);

    }





}
