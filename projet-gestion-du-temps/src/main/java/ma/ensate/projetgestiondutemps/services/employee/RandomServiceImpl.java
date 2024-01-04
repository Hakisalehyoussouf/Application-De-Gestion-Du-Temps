package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.entities.Random;
import ma.ensate.projetgestiondutemps.repositories.RandomRepository;
import ma.ensate.projetgestiondutemps.services.employee.RandomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RandomServiceImpl implements RandomService {

    private final RandomRepository randomRepository;

    public RandomServiceImpl(RandomRepository randomRepository) {
        this.randomRepository = randomRepository;
    }

    @Override
    public Random getRandom() {
        return randomRepository.findAll()
                .stream()
                .findFirst()
                .get();

    }
}
