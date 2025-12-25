package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ already exists
    Optional<User> findByEmail(String email);

    // ✅ ADD THIS (CRITICAL)
    Optional<User> findByUsername(String username);
}
