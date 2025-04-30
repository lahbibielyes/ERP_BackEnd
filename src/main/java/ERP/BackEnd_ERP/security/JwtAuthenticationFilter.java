package ERP.BackEnd_ERP.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ERP.BackEnd_ERP.model.User;
import ERP.BackEnd_ERP.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService ;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read credentials", e);
        }
    }
    

    @Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                         FilterChain chain, Authentication authResult) 
                                         throws IOException, ServletException {

    // Récupération de l’utilisateur depuis la base de données.
    User user = userService.findByUsername(authResult.getName());
    
    // Vérification du status. Par exemple, nous attendons "activé" pour un compte actif.
    if (!"Activé".equalsIgnoreCase(user.getStatus())) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "Votre compte est désactivé ! Veuillez contacter l'administrateur.");
        new ObjectMapper().writeValue(response.getOutputStream(), responseMap);
        return;
    }

    // Si le compte est actif, génération du token JWT et retour de la réponse.
    String token = jwtUtils.generateToken(authResult.getName());

    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("token", token);
    responseMap.put("username", user.getUsername());
    responseMap.put("email", user.getEmail());
    responseMap.put("id", String.valueOf(user.getId()));
    responseMap.put("role", user.getRole());

    response.setContentType("application/json");
    response.setStatus(HttpStatus.OK.value());
    new ObjectMapper().writeValue(response.getOutputStream(), responseMap);
}


@Override
protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          AuthenticationException failed)
        throws IOException, ServletException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("error", "Nom d'utilisateur ou mot de passe incorrect !");
    
    new ObjectMapper().writeValue(response.getOutputStream(), responseMap);
}

}