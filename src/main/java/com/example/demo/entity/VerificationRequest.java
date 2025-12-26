package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private String requestedBy;
    private String verificationMethod;
    private String status;
    private LocalDateTime verifiedAt;
    private String resultMessage;

    public Long getId() { return id; }
    public Long getCredentialId() { return credentialId; }
    public void setStatus(String status) { this.status = status; }
    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}
