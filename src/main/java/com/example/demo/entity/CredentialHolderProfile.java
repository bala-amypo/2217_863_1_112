package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderId;
    private String fullName;
    private String email;
    private String organization;
    private boolean active = true;

    public Long getId() { return id; }
    public String getHolderId() { return holderId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getOrganization() { return organization; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setHolderId(String holderId) { this.holderId = holderId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setOrganization(String organization) { this.organization = organization; }
    public void setActive(boolean active) { this.active = active; }
}
