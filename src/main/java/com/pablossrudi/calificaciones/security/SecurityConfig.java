package com.pablossrudi.calificaciones.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;
    @Value("${FRONT_URL}")
    private String frontUrl;

    @Autowired
    public SecurityConfig(final JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(frontUrl));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf((AbstractHttpConfigurer::disable))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Unauthorized" + authException.getMessage());
                        })
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/register").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/hash-password").permitAll()
                        .requestMatchers("/api/users/{userId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/users").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")

                        .requestMatchers(HttpMethod.POST,"/api/alumnos").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/alumnos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.GET,"/api/alumnos/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.GET,"/api/alumnos/rut/{rut}").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/alumnos/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/alumnos/{id}").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/materias").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/materias").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.GET,"/api/materias/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/materias/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/materias/{id}").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/alumnoMateria").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/alumnoMateria/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/alumnoMateria/{id}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/alumnoMateria/{id}").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
