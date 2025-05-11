package com.project_adventure.lab.security;

import com.project_adventure.lab.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public routes
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        // Routes protected by role
                        .requestMatchers(HttpMethod.GET, "/api/franchise/").hasAnyRole("CREATOR", "EDITOR", "PLAYER")
                        .requestMatchers(HttpMethod.GET, "/api/game/").hasAnyRole("CREATOR", "EDITOR", "PLAYER")

                        .requestMatchers(HttpMethod.POST, "/api/game").hasRole("CREATOR")
                        .requestMatchers(HttpMethod.POST, "/api/franchise").hasRole("CREATOR")

                        .requestMatchers(HttpMethod.PUT, "/api/game/**").hasRole("EDITOR")
                        .requestMatchers(HttpMethod.PUT, "/api/franchise/**").hasRole("EDITOR")
                        .requestMatchers(HttpMethod.PUT, "/api/player/**").hasRole("EDITOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/game/**").hasRole("EDITOR")

                        .requestMatchers("/api/player/**").hasRole("PLAYER")
                        // All other routes require authentication
                        .anyRequest().authenticated()
                )
                // Add our filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
