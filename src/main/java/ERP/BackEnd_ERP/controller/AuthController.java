package ERP.BackEnd_ERP.controller;

import java.util.HashMap;
import java.util.Map;

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
public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody UserResetPasswordRequest request) {
    User user = userService.findByUsernameAndEmail(request.getUsername(), request.getEmail());

    if (user != null) {
        System.out.println(user);
        emailService.sendPasswordResetEmail(request.getEmail(), user);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Un email de réinitialisation de mot de passe a été envoyé.");
        
        return ResponseEntity.ok(response);
    }

    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("success", false);
    errorResponse.put("message", "Aucun utilisateur trouvé avec ce username et cet email.");
    
    return ResponseEntity.badRequest().body(errorResponse);
}

}