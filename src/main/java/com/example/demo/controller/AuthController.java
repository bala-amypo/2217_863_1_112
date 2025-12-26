package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setPassword(request.password);
        userService.registerUser(user);
        return user;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.email);
        if (user == null) {
            return new JwtResponse(null);
        }
        return new JwtResponse("JWT_TOKEN");
    }
}
