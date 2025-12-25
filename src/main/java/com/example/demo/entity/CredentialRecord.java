package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;
    private String credentialCode;
    private String title;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String credentialType;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    @PrePersist
    public void prePersist() {
        if (status == null) status = "ACTIVE";
    }

    // getters & setters (all)
    public Long getId() { return id; }
    public Long getHolderId() { return holderId; }
    public String getCredentialCode() { return credentialCode; }
    public String getStatus() { return status; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setId(Long id) { this.id = id; }
    public void setHolderId(Long holderId) { this.holderId = holderId; }
    public void setCredentialCode(String credentialCode) { this.credentialCode = credentialCode; }
    public void setStatus(String status) { this.status = status; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
