package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private String status;
    private LocalDateTime verifiedAt;

    public Long getId() { return id; }
    public Long getCredentialId() { return credentialId; }
    public String getStatus() { return status; }
    public LocalDateTime getVerifiedAt() { return verifiedAt; }

    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }
    public void setStatus(String status) { this.status = status; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}

