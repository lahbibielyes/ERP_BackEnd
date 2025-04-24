package ERP.BackEnd_ERP.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ERP.BackEnd_ERP.model.User;
import ERP.BackEnd_ERP.service.EmailService;
import ERP.BackEnd_ERP.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = emailService.sendNewAccountEmail(user.getEmail(),user);
        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/updateRoleByAdmin/{id}")
    public ResponseEntity<?> updateRoleUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = emailService.sendUpdateRoleEmail(user.getEmail(), user);

        
        return ResponseEntity.ok(updatedUser);
    }


    @PutMapping("/updateStatusByAdmin/{id}")
    public ResponseEntity<?> updateStatusUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = emailService.sendAccountChangeStatusEmail(user.getEmail(), user);

        return ResponseEntity.ok(updatedUser);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> findUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.findUsersByRole(role));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    
    
}
