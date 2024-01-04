package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.dtos.TempsTravailRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.mappers.TempsTravailMapper;
import ma.ensate.projetgestiondutemps.repositories.TempsTravailRepository;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import ma.ensate.projetgestiondutemps.services.employee.TempsTravailService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TempsTravailServiceImpl implements TempsTravailService {


    private final TempsTravailRepository tempsTravailRepository;
    private final TempsTravailMapper tempsTravailMapper;

    private  final UsersRepository usersRepository;


    public TempsTravailServiceImpl(TempsTravailRepository tempsTravailRepository, TempsTravailMapper tempsTravailMapper, UsersRepository usersRepository) {
        this.tempsTravailRepository = tempsTravailRepository;
        this.tempsTravailMapper = tempsTravailMapper;
        this.usersRepository = usersRepository;
    }

    @Override
    public TempsTravailResponseDTO save(TempsTravailRequestDTO tempsTravailRequestDTO, String email) {

        /**
         * define parameters values
         */
        tempsTravailRequestDTO.setDateDebut(new Date());


        /**
         * find user
         */
        Users user = this.usersRepository.findByEmail(email);

        /**
         * Saved working time
         */
        TempsTravail tempsTravail =  tempsTravailMapper.tempsTravailRequestDTOToTempsTravail(tempsTravailRequestDTO);
        tempsTravail.setUser(user);
        TempsTravail savedTempsTravail = this.tempsTravailRepository.save(tempsTravail);

        TempsTravailResponseDTO tempsTravailResponseDTO = this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(savedTempsTravail);

        return tempsTravailResponseDTO;

    }



    @Override
    public TempsTravailResponseDTO update(Long id, TempsTravailRequestDTO tempsTravailRequestDTO) {

        /**
         * define parameters values
         */
        tempsTravailRequestDTO.setDateFin(new Date());


        /**
         * update work time
         */
        TempsTravail sendedTempsTravail = this.tempsTravailMapper.tempsTravailRequestDTOToTempsTravail(tempsTravailRequestDTO);
        TempsTravail existingTempsTravail = this.tempsTravailRepository.findById(id).get();
        TempsTravail existingTempsTravail = this.tempsTravailRepository.findById(id).get();

        existingTempsTravail.setDateFin(sendedTempsTravail.getDateFin());

        TempsTravail updatedTempsTravail = this.tempsTravailRepository.save(existingTempsTravail);



        return this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(updatedTempsTravail);
    }



    @Override
    public TempsTravailResponseDTO getWorkTime(Long id) {

        TempsTravail tempsTravail = this.tempsTravailRepository.findById(id).get();
        return this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(tempsTravail);
    }

    @Override
    public boolean verifyStarting(String email) {

        /**
         * user search
         */
        Users user = this.usersRepository.findByEmail(email);

        /**
         * Verification
         */
        TempsTravail tempsTravail = this.tempsTravailRepository.findLatestByUser(user).orElse(null);
        System.out.println("topId : " + tempsTravail.getId());

        Date today = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);


        if(tempsTravail!=null && isSameDay(tempsTravail.getDateDebut(), todayCal)){
            System.out.println("debut true");
            return true;
        }
        System.out.println("debut false");
        return false;
    }




    @Override
    public boolean verifyFinishing(String email) {
        /**
         * user search
         */
        Users user = this.usersRepository.findByEmail(email);

        /**
         * Verification
         */
        TempsTravail tempsTravail = this.tempsTravailRepository.findLatestByUser(user).orElse(null);


        Date today = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);


        if(tempsTravail!=null && tempsTravail.getDateFin()!=null && isSameDay(tempsTravail.getDateFin(), todayCal) ){
            System.out.println("fin true");
            return true;
        }
        System.out.println("fin false");
        return false;
    }



    @Override
    public List<TempsTravailResponseDTO> allWorkTimes(Pageable pageable, String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();
        /**
         * Avec celui d'auourdhui
         */
        List<TempsTravail> tempsTravails = this.tempsTravailRepository.findAllByUser(user, pageable);


        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsTravail> tempsTravails1 = tempsTravails
                .stream()
                .filter(tempsTravail -> !isSameDay(tempsTravail.getDateDebut(), todayCal))
                .collect(Collectors.toList());



        List<TempsTravailResponseDTO> tempsTravailResponseDTOS = tempsTravails1
                .stream()
                .map(tempsTravail -> this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(tempsTravail))
                .collect(Collectors.toList());

        return tempsTravailResponseDTOS;

    }



    @Override
    public List<TempsTravailResponseDTO> allTodayWorkTime(Pageable pageable, String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();

        /**
         * Avec celui d'auourdhui
         */
        List<TempsTravail> tempsTravails = this.tempsTravailRepository.findAllByUser(user, pageable);


        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsTravail> tempsTravails1 = tempsTravails
                .stream()
                .filter(tempsTravail -> isSameDay(tempsTravail.getDateDebut(), todayCal))
                .collect(Collectors.toList());



        List<TempsTravailResponseDTO> tempsTravailResponseDTOS = tempsTravails1
                .stream()
                .map(tempsTravail -> this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(tempsTravail))
                .collect(Collectors.toList());

        return tempsTravailResponseDTOS;

    }


    @Override
    public TempsTravailResponseDTO TodayWorkTimeOnly(String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();

        /**
         * Avec celui d'auourdhui
         */
        List<TempsTravail> tempsTravails = this.tempsTravailRepository.findAllByUser(user);


        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsTravail> tempsTravails1 = tempsTravails
                .stream()
                .filter(tempsTravail -> isSameDay(tempsTravail.getDateDebut(), todayCal))
                .collect(Collectors.toList());

        System.out.println("longeur" + tempsTravails1.size());

        System.out.println("aujourd'hui" + tempsTravails1.get(0).getId());


        List<TempsTravailResponseDTO> tempsTravailResponseDTOS = tempsTravails1
                .stream()
                .map(tempsTravail -> this.tempsTravailMapper.tempsTravailToTempsTravailResponseDTO(tempsTravail))
                .collect(Collectors.toList());

        return tempsTravailResponseDTOS.get(0);
    }




    private boolean isSameDay(Date date, Calendar cal) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);


        boolean resutl = dateCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
                dateCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
                dateCal.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH);

        System.out.println(resutl);

        return resutl;

    }

}
