package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ Spring creates the bean automatically
public class UserServiceImpl implements UserService {

    // Temporary in-memory storage (replace with DB later)
    private final List<User> users = new ArrayList<>();

    @Override
    public User registerUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail() != null
                        && u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
