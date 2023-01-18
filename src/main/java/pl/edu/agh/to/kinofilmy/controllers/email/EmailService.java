package pl.edu.agh.to.kinofilmy.controllers.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    @Value("classpath:email.properties")
    private Resource emailPropertiesFile;

    private String businessEmail = "kinofilmy@op.pl";
    private String businessEmailPassword = "3S4zrnixdVV5UxR-";

    private EmployeeService employeeService;

    public EmailService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public boolean sendNotificationToAllEmployees(String subject, String text){
        try {
            Session session = createSession();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(businessEmail);

            for(String toAddress: employeeService.getAllEmployeeEmailAddresses()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }

            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            return true;

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Session createSession() throws IOException {
        Properties properties = new Properties();
        properties.load(emailPropertiesFile.getInputStream());

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(businessEmail, businessEmailPassword);
            }
        });

        session.setDebug(true);

        return session;
    }
}
