package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Method name matches controller
    public User registerUser(User user) {

        repository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
