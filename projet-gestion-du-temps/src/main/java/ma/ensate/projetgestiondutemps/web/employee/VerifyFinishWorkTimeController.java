package ma.ensate.projetgestiondutemps.web.employee;

import lombok.AllArgsConstructor;
import ma.ensate.projetgestiondutemps.services.employee.HumeurService;
import ma.ensate.projetgestiondutemps.services.employee.TempsPauseService;
import ma.ensate.projetgestiondutemps.services.employee.TempsTravailService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee/verifies")
@CrossOrigin("*")
@AllArgsConstructor
public class VerifyFinishWorkTimeController {


    private final TempsTravailService tempsTravailService;
    private final TempsPauseService tempsPauseService;
    private final HumeurService humeurService;
    private final JwtDecoder jwtDecoder;


    @PostMapping("/finish")
    public boolean finishWork(@RequestBody String body,
                             @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        boolean found = this.tempsTravailService.verifyFinishing(email);

        return found;
    }

    @PostMapping("/finishbreak")
    public boolean finishBreak(@RequestBody String body,
                              @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        boolean found = this.tempsPauseService.verifyFinishing(email);

        return found;
    }


    @PostMapping("/finishhumeur")
    public boolean finishHumeur(@RequestBody String body,
                               @RequestHeader("Authorization") String jwtToken){

        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        boolean found = this.humeurService.verifyFinishing(email);

        return found;
    }


}
