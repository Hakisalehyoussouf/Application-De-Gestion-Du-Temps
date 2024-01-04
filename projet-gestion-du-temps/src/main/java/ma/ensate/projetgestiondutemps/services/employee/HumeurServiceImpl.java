package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.dtos.HumeurRequestDTO;
import ma.ensate.projetgestiondutemps.dtos.HumeurResponseDTO;
import ma.ensate.projetgestiondutemps.entities.Humeur;
import ma.ensate.projetgestiondutemps.mappers.HumeurMapper;
import ma.ensate.projetgestiondutemps.repositories.HumeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class HumeurServiceImpl implements HumeurService {

    private final HumeurRepository humeurRepository;
    private final HumeurMapper humeurMapper;
    private final VerifyTokenService verifyTokenService;
    private final RandomService randomService;
    private final JdbcTemplate jdbcTemplate;

    public HumeurServiceImpl(HumeurRepository humeurRepository,
                             HumeurMapper humeurMapper,
                             VerifyTokenService verifyTokenService,
                             RandomService randomService,
                             JdbcTemplate jdbcTemplate) {
        this.humeurRepository = humeurRepository;
        this.humeurMapper = humeurMapper;
        this.verifyTokenService = verifyTokenService;
        this.randomService = randomService;
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public HumeurResponseDTO save(HumeurRequestDTO humeurRequestDTO, String email) {

        /**
         * Random Number
         */
        System.out.println("random!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Long randomNumber = this.randomService.getRandom().getRandomNumber();

        /**
         *Concatenation
         */
        System.out.println("concatenation!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("email : " + email);
        System.out.println("rn : " + randomNumber);
        String emailRandomNumber = email + randomNumber;
        System.out.println("concate :" + emailRandomNumber);

        /**
         * le hash token
         */
        System.out.println("hashage!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String sql = "SELECT SHA2(?, 256)";
        String token = jdbcTemplate.queryForObject(sql, new Object[]{emailRandomNumber}, String.class);
        System.out.println(token);

        /**
         * token existing verify
         */

        if(this.verifyTokenService.findToken(token)){

            System.out.println("ajout en trouvant token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Humeur humeur = this.humeurMapper.humeurRequestDTOToHumeur(humeurRequestDTO);
            Humeur savedHumeur = this.humeurRepository.save(humeur);

            this.verifyTokenService.deleteByToken(token);

            HumeurResponseDTO humeurResponseDTO = new HumeurResponseDTO();
            humeurResponseDTO.setHumeur("true");

            return humeurResponseDTO;

        }else {

            System.out.println("echec de trouver le token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            HumeurResponseDTO humeurResponseDTO = new HumeurResponseDTO();
            humeurResponseDTO.setHumeur("false");

            return humeurResponseDTO;
        }

    }



    @Override
    public List<HumeurResponseDTO> getAllTodayFeedBack() {

        List<Humeur> humeurs = this.humeurRepository.findAllByDate(new Date());

        List<HumeurResponseDTO> humeurResponseDTOS = humeurs
                .stream()
                .map(humeur -> this.humeurMapper.humeurToHumeurResponseDTO(humeur))
                .collect(Collectors.toList());



        return humeurResponseDTOS;
    }



    @Override
    public boolean verifyFinishing(String email) {

        /**
         * Random Number
         */
        Long randomNumber = this.randomService.getRandom().getRandomNumber();


        /**
         *Concatenation
         */
        String emailRandomNumber = email + randomNumber;

        /**
         * le hash token
         */
        String sql = "SELECT SHA2(?, 256)";
        String token = jdbcTemplate.queryForObject(sql, new Object[]{emailRandomNumber}, String.class);

        /**
         * token existing verify
         */

        if(this.verifyTokenService.findToken(token)){
            return false;
        }else {
            return true;
        }

    }






    @Autowired
    private HumeurRepository humeurRepo;

    @Override
    public List<Humeur> getAllHumeurs() {
        return humeurRepo.findAllByOrderByDateDesc();
    }
}
