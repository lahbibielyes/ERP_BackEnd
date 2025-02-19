package ERP.BackEnd_ERP.config;

import ERP.BackEnd_ERP.security.JwtAuthenticationFilter;
import ERP.BackEnd_ERP.security.JwtAuthorizationFilter;
import ERP.BackEnd_ERP.security.JwtUtils;
import ERP.BackEnd_ERP.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public SecurityConfig(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

  

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Accès libre à /api/auth/**
                .anyRequest().authenticated() // Les autres routes nécessitent une authentification
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationManager(authenticationManager);
    
        // Ajout des filtres de sécurité JWT
        http.addFilter(new JwtAuthenticationFilter(authenticationManager, jwtUtils));
        http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager, jwtUtils), UsernamePasswordAuthenticationFilter.class);
    
        return http.build();
    }
    
}