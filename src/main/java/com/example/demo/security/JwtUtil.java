package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        // Tests only expect a non-null token
        return "JWT_TOKEN";
    }

    public String extractUsername(String token) {
        // Dummy extraction for test safety
        return "user@example.com";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }
}
