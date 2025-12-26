package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;

    @Column(unique = true)
    private String credentialCode;

    private String title;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String credentialType;
    private String status = "VALID";

    @Column(length = 5000)
    private String metadataJson;

    @ManyToMany
    private Set<VerificationRule> rules = new HashSet<>();

    public Long getId() { return id; }
    public Long getHolderId() { return holderId; }
    public String getCredentialCode() { return credentialCode; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Set<VerificationRule> getRules() { return rules; }
}
