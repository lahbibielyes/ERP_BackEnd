package ERP.BackEnd_ERP.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ERP.BackEnd_ERP.model.User;

@Service
public class EmailService {
     @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserService userService;

    public void sendPasswordResetEmail(String toEmail, User u) {
        String newPassword = generateRandomPassword();
    
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("erptalyscompany@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Réinitialisation de votre mot de passe");
    
        message.setText(
            "Bonjour " + u.getFirstname() + ",\n\n" +
            "Nous avons bien reçu votre demande de réinitialisation de mot de passe.\n" +
            "Voici votre nouveau mot de passe temporaire : " + newPassword + "\n\n" +
            "Pour des raisons de sécurité, nous vous recommandons de le modifier dès votre prochaine connexion.\n\n" +
            "Si vous n'êtes pas à l'origine de cette demande, veuillez contacter notre support technique immédiatement.\n\n" +
            "Cordialement,\n" +
            "L'équipe ERP Talys Company\n" +
            "erptalyscompany@gmail.com"
        );
    
        emailSender.send(message);
    
        u.setPassword(newPassword);
        userService.updateUser(u.getId(), u);
    }


    public User sendNewAccountEmail(String toEmail, User u) {
        String newPassword = generateRandomPassword();
    
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("erptalyscompany@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Création de votre compte ERP Talys");
    
        message.setText(
            "Bonjour " + u.getFirstname() + ",\n\n" +
            "Bienvenue chez ERP Talys Company !\n\n" +
            "Votre compte a été créé avec succès. Voici vos identifiants de connexion :\n" +
            "Nom d'utilisateur : " + u.getUsername() + "\n" +
            "Mot de passe temporaire : " + newPassword + "\n\n" +
            "Nous vous recommandons vivement de modifier ce mot de passe dès votre première connexion pour garantir la sécurité de votre compte.\n\n" +
            "Si vous n’avez pas demandé la création de ce compte, veuillez contacter notre support technique immédiatement.\n\n" +
            "Cordialement,\n" +
            "L'équipe ERP Talys Company\n" +
            "erptalyscompany@gmail.com"
        );
    
        emailSender.send(message);
    
        u.setPassword(newPassword);
       return userService.registerUser(u);
    }
    


    public User sendUpdateRoleEmail(String toEmail, User u) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("erptalyscompany@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Mise à jour de votre rôle");
    
        message.setText(
            "Bonjour " + u.getFirstname() + ",\n\n" +
            "Nous vous informons que votre rôle au sein de la plateforme ERP Talys Company a été mis à jour.\n\n" +
            "Nouveau rôle attribué : " + u.getRole() + "\n\n" +
            "Si vous pensez qu’il s’agit d’une erreur ou si vous avez des questions, merci de contacter notre support technique.\n\n" +
            "Cordialement,\n" +
            "L'équipe ERP Talys Company\n" +
            "erptalyscompany@gmail.com"
        );
    
        emailSender.send(message);
    
        return userService.updateUserExceptPassword(u.getId(), u);
    }


    public User sendAccountChangeStatusEmail(String toEmail, User u) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("erptalyscompany@gmail.com");
        message.setTo(toEmail);
        

        if(u.getStatus().equalsIgnoreCase("Désactivé")) {
            message.setSubject("Désactivation de votre compte ERP Talys");
            message.setText(
            "Bonjour " + u.getFirstname() + ",\n\n" +
            "Nous vous informons que votre compte sur la plateforme ERP Talys Company a été désactivé.\n\n" +
            "En conséquence, vous ne pourrez plus accéder à votre espace personnel ni utiliser les fonctionnalités associées à votre rôle actuel.\n\n" +
            "Si vous pensez qu’il s’agit d’une erreur ou si vous avez des questions, veuillez contacter notre support technique.\n\n" +
            "Cordialement,\n" +
            "L'équipe ERP Talys Company\n" +
            "erptalyscompany@gmail.com"
        );
   
        }
        else{
            message.setSubject("Réactivation de votre compte ERP Talys");
            message.setText(
        "Bonjour " + u.getFirstname() + ",\n\n" +
        "Nous avons le plaisir de vous informer que votre compte sur la plateforme ERP Talys Company a été réactivé avec succès.\n\n" +
        "Vous pouvez à nouveau vous connecter et accéder à toutes les fonctionnalités correspondant à votre rôle : " + u.getRole() + ".\n\n" +
        "Si vous avez besoin d’assistance ou si vous rencontrez des difficultés, n’hésitez pas à contacter notre support technique.\n\n" +
        "Cordialement,\n" +
        "L'équipe ERP Talys Company\n" +
        "erptalyscompany@gmail.com"
    );
        }
       
        emailSender.send(message);
        return userService.updateUserExceptPassword(u.getId(), u);
    }
    
    

    
    

    private String generateRandomPassword() {
        // Génère un mot de passe aléatoire
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-*$#@!";
        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = rand.nextInt(chars.length());
            password.append(chars.charAt(randomIndex));
        }

        return password.toString();
    }
}
