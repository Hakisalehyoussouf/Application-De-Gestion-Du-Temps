package ma.ensate.projetgestiondutemps.web.admin;

import ma.ensate.projetgestiondutemps.entities.Humeur;
import ma.ensate.projetgestiondutemps.services.employee.HumeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@CrossOrigin("*")
@Controller
public class AdminHummeurController {

    private final HumeurService humeurService;

    @Autowired
    private AdminHummeurController(HumeurService humeurService) {this.humeurService = humeurService;}

    @GetMapping("/getAllHumeurs")
    public ResponseEntity<List<Humeur>> getAllHumeurs() {
        List<Humeur> humeurs = humeurService.getAllHumeurs();
        return new ResponseEntity<>(humeurs, HttpStatus.OK);
    }

}
