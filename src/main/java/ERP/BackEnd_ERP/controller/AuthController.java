package ERP.BackEnd_ERP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.User;
import ERP.BackEnd_ERP.model.UserResetPasswordRequest;
import ERP.BackEnd_ERP.service.EmailService;
import ERP.BackEnd_ERP.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

      @Autowired
    private EmailService emailService;

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody UserResetPasswordRequest request) {
        // Vérifier si l'utilisateur existe dans la base de données avec son username et email
        // Exemple : vérifier dans une base de données d'utilisateurs

        // Si l'utilisateur existe, envoyer un email
        emailService.sendPasswordResetEmail(request.getEmail());

        return "Un email de réinitialisation de mot de passe a été envoyé.";
    }

    
}