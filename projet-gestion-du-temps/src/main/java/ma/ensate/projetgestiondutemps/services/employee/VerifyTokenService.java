package ma.ensate.projetgestiondutemps.services.employee;




public interface VerifyTokenService {

    boolean findToken(String token);

    void deleteByToken(String token);
}
