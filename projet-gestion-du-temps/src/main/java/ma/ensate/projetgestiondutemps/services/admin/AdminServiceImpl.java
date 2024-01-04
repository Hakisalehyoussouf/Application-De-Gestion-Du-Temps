package ma.ensate.projetgestiondutemps.services.admin;

import ma.ensate.projetgestiondutemps.dtos.UsersRequestDTO;
import ma.ensate.projetgestiondutemps.entities.TempsPause;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.enums.Role;
import ma.ensate.projetgestiondutemps.repositories.AdminRepo;
import ma.ensate.projetgestiondutemps.repositories.TempsPauseRepository;
import ma.ensate.projetgestiondutemps.repositories.TempsTravailRepository;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo repo;

    private final UsersRepository usersRepository;

    private final TempsTravailRepository tempsTravailRepository;

    private final TempsPauseRepository tempsPauseRepository;

    public AdminServiceImpl(UsersRepository usersRepository, TempsTravailRepository tempsTravailRepository, TempsPauseRepository tempsPauseRepository) {
        this.usersRepository = usersRepository;
        this.tempsTravailRepository = tempsTravailRepository;
        this.tempsPauseRepository = tempsPauseRepository;
    }

    @Override
    public Users addEmployee(UsersRequestDTO usersRequestDTO) {
        Users user = new Users();
        user.setNom(usersRequestDTO.getNom());
        user.setPrenom(usersRequestDTO.getPrenom());
        user.setEmail(usersRequestDTO.getEmail());
        user.setPassword(usersRequestDTO.getPassword());
        user.setRole(Role.EMPLOYEE);

        return repo.save(user);
    }
    @Override
    public List<Users> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Users getUserById(long id) {
        Optional<Users> u = repo.findById(id);
        if (u.isPresent()) {
            return u.get();
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<Users> getUserByPaginate(int currentPage, int size) {
        Pageable p = PageRequest.of(currentPage, size);
        return repo.findAll(p);
    }



    @Override
    public String getFullName(Long id) {

        Users user = this.usersRepository.findById(id).get();
        String fullName = user.getNom() + " " + user.getPrenom();

        System.out.println(fullName);
        System.out.println(fullName);
        System.out.println(fullName);
        System.out.println(fullName);
        System.out.println(fullName);
        System.out.println(fullName);
        System.out.println(fullName);
        return fullName;
    }

    @Override
    public Long getWorkHours(Long id) {



        Users user = this.usersRepository.findById(id).get();

        // Obtenez la date de début de la semaine (le lundi)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startDate = calendar.getTime();

        // Obtenez la date de fin de la semaine (le samedi)
        calendar.add(Calendar.DAY_OF_WEEK, 6); // Ajoute 6 jours pour aller au samedi
        Date endDate = calendar.getTime();


        // Obtenez les heures de travail pour la semaine en cours
        List<TempsTravail> workHoursThisWeek = this.tempsTravailRepository.findByDateDebutBetweenAndUserEquals(startDate, endDate,user);

        Long sumTravail = 0L;


        for (TempsTravail whw : workHoursThisWeek) {
            Date dateDebut = whw.getDateDebut();
            Date dateFin = whw.getDateFin();

            Long differenceInMilliseconds = dateFin.getTime() - dateDebut.getTime();
//            Long differenceInHours = TimeUnit.MILLISECONDS.toHours(differenceInMilliseconds);
            Long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMilliseconds);

            sumTravail += differenceInMinutes;

        }






        // Obtenez les heures de travail pour la semaine en cours
        List<TempsPause> breakHoursThisWeek = this.tempsPauseRepository.findByDateDebutBetweenAndUserEquals(startDate, endDate,user);

        Long sumPause = 0L;


        for (TempsPause bhw : breakHoursThisWeek) {
            Date dateDebut = bhw.getDateDebut();
            Date dateFin = bhw.getDateFin();

            Long differenceInMilliseconds = dateFin.getTime() - dateDebut.getTime();
//            Long differenceInHours = TimeUnit.MILLISECONDS.toHours(differenceInMilliseconds);
            Long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMilliseconds) ;

            sumPause += differenceInMinutes;


        }

        Long sum = 0L;
        sum = sumTravail-sumPause;

        Long sumHeures = sum/60;


        if ((sum % 60 )> 30) {
            sumHeures += 1;
        }

        return sumHeures;
    }

    @Override
    public Long getOverHours(Long id) {

        Long tmp = this.getWorkHours(id);
        if(tmp>40L){
            return (tmp-40L);
        }
        return 0L;

    }


}
