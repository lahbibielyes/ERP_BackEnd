package ERP.BackEnd_ERP.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
     @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String toEmail) {
        String newPassword = generateRandomPassword();

        // Envoie du mail avec un nouveau mot de passe
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mechergui.aziz843@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Votre nouveau mot de passe est : " + newPassword);

        emailSender.send(message);
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
