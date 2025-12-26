package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 🔐 PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔐 IN-MEMORY USER FOR SWAGGER LOGIN
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails user = User
                .withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // 🔐 SECURITY FILTER CHAIN
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
                    .authenticated()
                    .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 🔐 JWT FILTER BEAN (REQUIRED FOR TESTS)
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtUtil jwtUtil,
            CustomUserDetailsService userDetailsService) {

        return new JwtAuthenticationFilter(jwtUtil, userDetailsService);
    }
}
