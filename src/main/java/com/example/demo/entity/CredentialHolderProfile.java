package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_holder_profiles")
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String holderId;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String organization;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

   

    public Long getId() {
        return id;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
