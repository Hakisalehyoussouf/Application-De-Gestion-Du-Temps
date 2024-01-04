package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.entities.VerifyToken;
import ma.ensate.projetgestiondutemps.enums.Role;
import ma.ensate.projetgestiondutemps.repositories.RandomRepository;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import ma.ensate.projetgestiondutemps.repositories.VerifyTokenRepositpory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Random;

@Service
@Transactional
public class GenerateRandomAndToken {

    private final RandomRepository randomRepository;
    private final VerifyTokenRepositpory verifyTokenRepositpory;

    private final UsersRepository usersRepository;
    private final JdbcTemplate jdbcTemplate;

    public GenerateRandomAndToken(RandomRepository randomRepository, VerifyTokenRepositpory verifyTokenRepositpory, UsersRepository usersRepository, JdbcTemplate jdbcTemplate) {
        this.randomRepository = randomRepository;
        this.verifyTokenRepositpory = verifyTokenRepositpory;
        this.usersRepository = usersRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void UpdateRandom(){

        Random random =  new Random();
        Long randomNumber = random.nextLong();

        ma.ensate.projetgestiondutemps.entities.Random existingrandom = this.randomRepository.findById(1L).get();
        existingrandom.setRandomNumber(randomNumber);
        ma.ensate.projetgestiondutemps.entities.Random updatedgrandom = this.randomRepository.save(existingrandom);

        return;
    }


    @Scheduled(cron = "0 10 0 * * ?")
    public void GenerateToken(){

        this.verifyTokenRepositpory.deleteAll();


        Role role = Role.EMPLOYEE;
        List<Users> employees  = this.usersRepository.findAllByRole(role);

        ma.ensate.projetgestiondutemps.entities.Random existingrandom = this.randomRepository.findById(1L).get();
        Long randomNumber = existingrandom.getRandomNumber();

        /**
         *Concatenation
         */

        employees.forEach(employee->{

            String emailRandomNumber = employee.getEmail() + randomNumber;
            System.out.println("ch email :" + employee.getEmail());
            System.out.println("ch rn :" + randomNumber);
            System.out.println("ch concate :" + emailRandomNumber);

            /**
             * le hash token
             */
            String sql = "SELECT SHA2(?, 256)";
            String token = jdbcTemplate.queryForObject(sql, new Object[]{emailRandomNumber}, String.class);

            /**
             * Insertion
             */
            VerifyToken verifyToken = new VerifyToken();
            verifyToken.setToken(token);
            VerifyToken insertedToken = this.verifyTokenRepositpory.save(verifyToken);


        });





    }


}
