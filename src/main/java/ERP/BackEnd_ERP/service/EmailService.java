package ERP.BackEnd_ERP.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ERP.BackEnd_ERP.model.User;

@Service
public class EmailService {
     @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public void sendPasswordResetEmail(String toEmail,User u) {
        String newPassword = generateRandomPassword();

        // Envoie du mail avec un nouveau mot de passe
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("erptalys@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Votre nouveau mot de passe est : " + newPassword);
        emailSender.send(message);
        u.setPassword(newPassword);
        userService.updateUser(u.getId(), u);


    }

    private String generateRandomPassword() {
        // Génère un mot de passe aléatoire
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = rand.nextInt(chars.length());
            password.append(chars.charAt(randomIndex));
        }

        return password.toString();
    }
}
