package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.dtos.TempsPauseRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsPauseResponseDTO;
import ma.ensate.projetgestiondutemps.dtos.TempsTravailResponseDTO;
import ma.ensate.projetgestiondutemps.entities.TempsPause;
import ma.ensate.projetgestiondutemps.entities.TempsTravail;
import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.mappers.TempsPauseMapper;
import ma.ensate.projetgestiondutemps.repositories.TempsPauseRepository;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import ma.ensate.projetgestiondutemps.services.employee.TempsPauseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TempsPauseServiceImpl implements TempsPauseService {


    private final TempsPauseRepository tempsPauseRepository;
    private final TempsPauseMapper tempsPauseMapper;

    private final UsersRepository usersRepository;

    public TempsPauseServiceImpl(TempsPauseRepository tempsPauseRepository, TempsPauseMapper tempsPauseMapper, UsersRepository usersRepository) {
        this.tempsPauseRepository = tempsPauseRepository;
        this.tempsPauseMapper = tempsPauseMapper;
        this.usersRepository = usersRepository;
    }



    @Override
    public TempsPauseResponseDTO save(TempsPauseRequestDTO tempsPauseRequestDTO, String email) {

        /**
         * define parameters values
         */
        tempsPauseRequestDTO.setDateDebut(new Date());

        /**
         * find user
         */
        Users user = this.usersRepository.findByEmail(email);

        /**
         * save break time
         */
        TempsPause tempsPause = this.tempsPauseMapper.tempsPauseRequestDTOToTempsPause(tempsPauseRequestDTO);
        tempsPause.setUser(user);
        TempsPause savedTempsPause = this.tempsPauseRepository.save(tempsPause);

        TempsPauseResponseDTO tempsPauseResponseDTO = this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(savedTempsPause);

        return tempsPauseResponseDTO;


    }

    @Override
    public TempsPauseResponseDTO update(Long id, TempsPauseRequestDTO tempsPauseRequestDTO) {

        /**
         * define parameters values
         */
        tempsPauseRequestDTO.setDateFin(new Date());


        /**
         * update break time
         */
        TempsPause sendedTempsPause = this.tempsPauseMapper.tempsPauseRequestDTOToTempsPause(tempsPauseRequestDTO);
        TempsPause existingTempsPause = this.tempsPauseRepository.findById(id).get();

        existingTempsPause.setDateFin(sendedTempsPause.getDateFin());

        TempsPause updatedTempsPause = this.tempsPauseRepository.save(existingTempsPause);

        return this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(updatedTempsPause);


    }

    @Override
    public TempsPauseResponseDTO getBreakTime(Long id) {
        TempsPause tempsPause = this.tempsPauseRepository.findById(id).get();
        return  this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(tempsPause);
    }



    @Override
    public List<TempsPauseResponseDTO> allBreakTime(Pageable pageable, String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();
        /**
         * Avec celui d'auourdhui
         */
        List<TempsPause> tempsPauses = this.tempsPauseRepository.findAllByUser(user, pageable);

        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsPause> tempsPauses1 = tempsPauses
                .stream()
                .filter(tempsPause -> isSameDay(tempsPause.getDateDebut(), todayCal))
                .collect(Collectors.toList());



        List<TempsPauseResponseDTO> tempsPauseResponseDTOS = tempsPauses1
                .stream()
                .map(tempsPause -> this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(tempsPause))
                .collect(Collectors.toList());



        return tempsPauseResponseDTOS;
    }



    @Override
    public List<TempsPauseResponseDTO> allTodayBreakTime(Pageable pageable, String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();

        /**
         * selon l'utilisateur
         */
        List<TempsPause> tempsPauses = this.tempsPauseRepository.findAllByUser(user, pageable);


        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsPause> tempsPauses1 = tempsPauses
                .stream()
                .filter(tempsTravail -> isSameDay(tempsTravail.getDateDebut(), todayCal))
                .collect(Collectors.toList());



        List<TempsPauseResponseDTO> tempsPauseResponseDTOS = tempsPauses1
                .stream()
                .map(tempsTravail -> this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(tempsTravail))
                .collect(Collectors.toList());

        return tempsPauseResponseDTOS;

    }




    @Override
    public TempsPauseResponseDTO TodayBreakTimeOnly(String email) {

        Users user =  this.usersRepository.findByEmail(email);

        Date date = new Date();
        int day = date.getDay();

        /**
         * Avec celui d'auourdhui
         */
        List<TempsPause> tempsPauses = this.tempsPauseRepository.findAllByUser(user);


        /**
         * sans celui d'auourdhui
         */
        Date today = new Date();

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        List<TempsPause> tempsPauses1 = tempsPauses
                .stream()
                .filter(tempsTravail -> isSameDay(tempsTravail.getDateDebut(), todayCal))
                .collect(Collectors.toList());

        System.out.println("longeur" + tempsPauses1.size());

        if(tempsPauses1.size()>0){
            System.out.println("aujourd'hui" + tempsPauses1.get(0).getId());
        }



        List<TempsPauseResponseDTO> tempsPauseResponseDTOS = tempsPauses1
                .stream()
                .map(tempsPause -> this.tempsPauseMapper.tempsPauseToTempsPauseResponseDTO(tempsPause))
                .collect(Collectors.toList());

        if(tempsPauseResponseDTOS.size()>0){
            return tempsPauseResponseDTOS.get(tempsPauseResponseDTOS.size()-1);
        }

        return tempsPauseResponseDTOS.get(0);


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







    @Override
    public boolean verifyStarting(String email) {

        /**
         * user search
         */
        Users user = this.usersRepository.findByEmail(email);

        /**
         * Verification
         */
        TempsPause tempsPause = this.tempsPauseRepository.findLatestByUser(user).orElse(null);
        if(tempsPause!=null){
            System.out.println("topId  pause: " + tempsPause.getId());
        }


        Date today = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);


        if(tempsPause!=null && isSameDay(tempsPause.getDateDebut(), todayCal) && tempsPause.getDateFin()==null){
            System.out.println("debut true pause");
            return true;
        }
        System.out.println("debut false pause");
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
        TempsPause tempsPause = this.tempsPauseRepository.findLatestByUser(user).orElse(null);


        Date today = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);


        if(tempsPause!=null && tempsPause.getDateFin()!=null && isSameDay(tempsPause.getDateFin(), todayCal) ){
            System.out.println("fin true pause");
            return true;
        }
        System.out.println("fin false pause");
        return false;
    }



}
