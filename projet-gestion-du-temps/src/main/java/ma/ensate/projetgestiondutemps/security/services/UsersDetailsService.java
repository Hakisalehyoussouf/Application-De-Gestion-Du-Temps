package ma.ensate.projetgestiondutemps.security.services;

import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import ma.ensate.projetgestiondutemps.security.utils.UsersDeatils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsersDetailsService implements UserDetailsService {


    private UsersRepository usersRepository;

    public UsersDetailsService(UsersRepository usersRepository) {
        super();
        this.usersRepository = usersRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user =  usersRepository.findByEmail(username);
        if(user==null) {
            throw new UsernameNotFoundException("Email ou mot de passe incorrect");
        }
        return  new UsersDeatils(user);
    }
}
