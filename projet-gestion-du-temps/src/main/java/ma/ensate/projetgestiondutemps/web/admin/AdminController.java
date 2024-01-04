package ma.ensate.projetgestiondutemps.web.admin;

import ma.ensate.projetgestiondutemps.dtos.UsersRequestDTO;
import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
public class AdminController {

    private final AdminService adminService;



    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Users> addEmployee(@RequestBody UsersRequestDTO userRequestDTO) {
        try {
            BCryptPasswordEncoder BCriptPasswordEncoder = new BCryptPasswordEncoder();
            userRequestDTO.setPassword(BCriptPasswordEncoder.encode(userRequestDTO.getPassword()));

            Users user = adminService.addEmployee(userRequestDTO);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Users>> getAllEmployees() {

            List<Users> employees = adminService.getAllUsers();
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        try {
            adminService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFullName/{id}")
    public ResponseEntity<String> getFullName(@PathVariable Long id) {

        String fullName = adminService.getFullName(id);
        System.out.println("c " + fullName);
        System.out.println("c " + fullName);
        System.out.println("c " + fullName);
        System.out.println("c " + fullName);
        System.out.println("c " + fullName);
        System.out.println("c " + fullName);


        return new ResponseEntity<>(fullName, HttpStatus.OK);
    }

    @GetMapping("/getWorkHours/{id}")
    public ResponseEntity<Long> getWorkHours(@PathVariable Long id) {

        Long workHours = adminService.getWorkHours(id);

        return new ResponseEntity<>(workHours, HttpStatus.OK);
    }

    @GetMapping("/getOverHours/{id}")
    public ResponseEntity<Long> getOverHours(@PathVariable Long id) {

        Long overHours = adminService.getOverHours(id);

        return new ResponseEntity<>(overHours, HttpStatus.OK);
    }






}
