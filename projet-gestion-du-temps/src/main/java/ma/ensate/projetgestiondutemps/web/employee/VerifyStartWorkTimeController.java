package ma.ensate.projetgestiondutemps.web.employee;

import lombok.AllArgsConstructor;
import ma.ensate.projetgestiondutemps.services.employee.TempsPauseService;
import ma.ensate.projetgestiondutemps.services.employee.TempsTravailService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee/verifies")
@CrossOrigin("*")
@AllArgsConstructor
public class VerifyStartWorkTimeController {


    private final TempsTravailService tempsTravailService;

    private final TempsPauseService tempsPauseService;
    private final JwtDecoder jwtDecoder;


    @PostMapping("/start")
    public boolean startWork(@RequestBody String body,
                             @RequestHeader("Authorization") String jwtToken){


        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        boolean found = this.tempsTravailService.verifyStarting(email);

        return found;
    }


    @PostMapping("/startbreak")
    public boolean startBreak(@RequestBody String body,
                             @RequestHeader("Authorization") String jwtToken){


        String token = jwtToken.replace("Bearer ", "");
        Jwt jwt = this.jwtDecoder.decode(token);
        String email = jwt.getSubject();

        boolean found = this.tempsPauseService.verifyStarting(email);

        return found;
    }

}
