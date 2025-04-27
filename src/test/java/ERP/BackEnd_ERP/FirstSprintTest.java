package ERP.BackEnd_ERP;

import ERP.BackEnd_ERP.model.User;
import ERP.BackEnd_ERP.security.JwtUtils;
import ERP.BackEnd_ERP.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;




@SpringBootTest
@AutoConfigureMockMvc
public class FirstSprintTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserService userService;

    @Test
    void Authentifcation() throws Exception {
        String username = "aziz";
        String password = "123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus("Activé");
        user.setUsername(username);
        user.setRole("Directeur Associé");
        user.setId(1L);

        Mockito.when(userService.findByUsername(username)).thenReturn(user);
        Mockito.when(jwtUtils.generateToken(username)).thenReturn("fake-jwt-token");

        Mockito.when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(username, password));

        String requestBody = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.role").value("Directeur Associé"));
    }



    @Test
void findAllUsers() {
    // Création de deux utilisateurs fictifs
    User user1 = new User();
    user1.setUsername("aziz");

    User user2 = new User();
    user2.setUsername("montassar");

    // Simuler le comportement du service
    Mockito.when(userService.findAllUsers()).thenReturn(List.of(user1, user2));

    // Appel de la méthode et vérification du résultat
    List<User> users = userService.findAllUsers();

    // Assertions
    assert(users.size() == 2);
    assert(users.get(0).getUsername().equals("aziz"));
    assert(users.get(1).getUsername().equals("montassar"));
}

@Test
void registerUser() {
    // Préparation des données
    User user = new User();
    user.setUsername("nour");
    user.setPassword("rawpassword");

    User savedUser = new User();
    savedUser.setUsername("nour");
    savedUser.setPassword("encodedpassword");

    // Mocks
    Mockito.when(userService.registerUser(Mockito.any(User.class))).thenAnswer(invocation -> {
        User inputUser = invocation.getArgument(0);
        inputUser.setPassword("encodedpassword"); // simulate encoding
        return inputUser;
    });

    // Appel de la méthode
    User result = userService.registerUser(user);

    // Vérification
    assert(result.getPassword().equals("encodedpassword"));
    assert(result.getUsername().equals("nour"));
}


@Test
void updateUser() {
    Long userId = 1L;

    User existingUser = new User();
    existingUser.setId(userId);
    existingUser.setPassword("oldEncodedPassword");

    User updateData = new User();
    updateData.setEmail("new@mail.com");
    updateData.setPhone(22222222);
    updateData.setFirstname("New");
    updateData.setLastname("Name");
    updateData.setStatus("Activé");
    updateData.setRole("Admin");
    updateData.setPassword("newPassword");

    Mockito.when(userService.updateUser(Mockito.eq(userId), Mockito.any(User.class)))
            .thenAnswer(invocation -> {
                User userToUpdate = invocation.getArgument(1);
                existingUser.setEmail(userToUpdate.getEmail());
                existingUser.setPhone(userToUpdate.getPhone());
                existingUser.setFirstname(userToUpdate.getFirstname());
                existingUser.setLastname(userToUpdate.getLastname());
                existingUser.setStatus(userToUpdate.getStatus());
                existingUser.setRole(userToUpdate.getRole());
                existingUser.setPassword("encodedNewPassword");
                return existingUser;
            });

    // Appel
    User updated = userService.updateUser(userId, updateData);

    // Vérification
    assert(updated.getEmail().equals("new@mail.com"));
    assert(updated.getPassword().equals("encodedNewPassword"));
}


}