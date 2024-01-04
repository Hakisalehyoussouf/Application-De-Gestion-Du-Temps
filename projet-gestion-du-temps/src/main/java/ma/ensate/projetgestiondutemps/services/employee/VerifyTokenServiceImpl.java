package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.entities.VerifyToken;
import ma.ensate.projetgestiondutemps.repositories.VerifyTokenRepositpory;
import ma.ensate.projetgestiondutemps.services.employee.VerifyTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VerifyTokenServiceImpl implements VerifyTokenService {

    private final VerifyTokenRepositpory verifyTokenRepositpory;

    public VerifyTokenServiceImpl(VerifyTokenRepositpory verifyTokenRepositpory) {
        this.verifyTokenRepositpory = verifyTokenRepositpory;
    }


    @Override
    public boolean findToken(String token) {

        VerifyToken verifyToken = this.verifyTokenRepositpory.findByToken(token);

        if(verifyToken!=null){
            return true;
        }

        return false;

    }

    @Override
    public void deleteByToken(String token) {
        this.verifyTokenRepositpory.deleteByToken(token);
    }
}
