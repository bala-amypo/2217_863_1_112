package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String holderId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String organization;
    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public String getHolderId() { return holderId; }
    public void setHolderId(String holderId) { this.holderId = holderId; }
    public String getEmail() { return email; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
