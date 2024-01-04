package ma.ensate.projetgestiondutemps.web.admin;

import ma.ensate.projetgestiondutemps.repositories.HumeurRepository;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@Controller
public class DashboardController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private HumeurRepository moodRepository;


    @GetMapping("/dashboard")
    public ResponseEntity<Long> getUserCount() {
        Long count = userRepository.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }



    @GetMapping("/totalMoods")
    public ResponseEntity<Long> getTotalMoods() {
        return new ResponseEntity<>(moodRepository.count(), HttpStatus.OK);
    }

}

