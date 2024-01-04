package ma.ensate.projetgestiondutemps.services.employee;


import ma.ensate.projetgestiondutemps.entities.Users;
import ma.ensate.projetgestiondutemps.enums.Role;
import ma.ensate.projetgestiondutemps.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
public class EmailJob {



    private final UsersRepository usersRepository;


    @Value("${mail.from}")
    private String from;

    @Value("${mail.password}")
    private String password;

    public EmailJob(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Scheduled(cron = "0 0 17 * * ?")
    public void sendScheduledEmails() {


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Role role = Role.EMPLOYEE;
        List<Users> employees = this.usersRepository.findAllByRole(role);

        employees.forEach(employee->{

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));
                message.setSubject("Ajouter Votre Humeur");
                message.setText("Pour soumettre votre humeur, cliquez sur le lien suivant : http://localhost:4200/employee/today");

                Transport.send(message);

                System.out.println("Mail successfully sent to " + employee.getEmail());
            } catch (MessagingException e) {
                System.out.println("Failed to send email to " + employee.getEmail() + ": " + e.getMessage());
            }

        });


    }
}
