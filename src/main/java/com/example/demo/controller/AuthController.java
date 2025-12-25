package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints for login and registration")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
    public AuthController(UserService userService, 
                         AuthenticationManager authenticationManager, 
                         JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Create a new user account")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {

        User user = new User();  // ✅ no-args constructor ONLY
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // prevent role abuse
        user.setRole("VIEWER");

        User savedUser = userService.registerUser(user);

        String token = jwtUtil.generateToken(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getRole()
            )     ;

        JwtResponse response = new JwtResponse(
            token,
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getRole()
            );

        return ResponseEntity.ok(response);
}

    
    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and generate JWT token")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        User user = userService.findByEmail(request.getEmail());
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        JwtResponse response = new JwtResponse(token, user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(response);
    }
}