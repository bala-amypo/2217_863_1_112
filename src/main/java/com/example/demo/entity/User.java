package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;
    private String role = "VIEWER";

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public void setPassword(String password) { this.password = password; }
}
