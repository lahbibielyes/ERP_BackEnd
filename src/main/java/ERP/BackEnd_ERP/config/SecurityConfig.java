package ERP.BackEnd_ERP.config;

import ERP.BackEnd_ERP.security.JwtAuthenticationFilter;
import ERP.BackEnd_ERP.security.JwtAuthorizationFilter;
import ERP.BackEnd_ERP.security.JwtUtils;
import ERP.BackEnd_ERP.service.UserService;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Ajout de CORS ici
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD

                .requestMatchers("/api/auth/**" , "/api/company/**","/api/user/**","/api/besoin/**","/api/contact/**","/api/historique_besoin/**","/api/type-actions/**").permitAll()
=======
                .requestMatchers("/api/auth/**" , "/api/company/**","/api/user/**","/api/besoin/**","/api/contact/**","/api/action-besoin/**").permitAll()
>>>>>>> 273009c2165c10dd6c1d9f26e43cb92e088cd299
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationManager(authenticationManager);

        // Ajout des filtres de sécurité JWT
        http.addFilter(new JwtAuthenticationFilter(authenticationManager, jwtUtils,userService));
        http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager, jwtUtils), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("http://localhost:4200")); // ✅ Remplace `setAllowedOrigins`
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}