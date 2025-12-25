package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credential_records")
public class CredentialRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long holderId;
    
    @Column(unique = true, nullable = false)
    private String credentialCode;
    
    private String title;
    
    private String issuer;
    
    private LocalDate issueDate;
    
    private LocalDate expiryDate;
    
    private String credentialType;
    
    private String status;
    
    @Column(length = 2000)
    private String metadataJson;
    
    @ManyToMany
    @JoinTable(
        name = "credential_rules",
        joinColumns = @JoinColumn(name = "credential_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<VerificationRule> rules = new HashSet<>();
    
    public CredentialRecord() {}
    
    public CredentialRecord(Long holderId, String credentialCode, String title, String issuer, 
                           String credentialType, String status, LocalDate expiryDate, String metadataJson) {
        this.holderId = holderId;
        this.credentialCode = credentialCode;
        this.title = title;
        this.issuer = issuer;
        this.credentialType = credentialType;
        this.status = status;
        this.expiryDate = expiryDate;
        this.metadataJson = metadataJson;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getHolderId() { return holderId; }
    public void setHolderId(Long holderId) { this.holderId = holderId; }
    
    public String getCredentialCode() { return credentialCode; }
    public void setCredentialCode(String credentialCode) { this.credentialCode = credentialCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    public String getCredentialType() { return credentialType; }
    public void setCredentialType(String credentialType) { this.credentialType = credentialType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
    
    public Set<VerificationRule> getRules() { return rules; }
    public void setRules(Set<VerificationRule> rules) { this.rules = rules; }
}
