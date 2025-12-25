package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;
    private String title;
    private String issuer;
    private LocalDate expiryDate;
    private String metadataJson;

    public Long getId() { return id; }
    public Long getHolderId() { return holderId; }
    public String getTitle() { return title; }
    public String getIssuer() { return issuer; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getMetadataJson() { return metadataJson; }

    public void setHolderId(Long holderId) { this.holderId = holderId; }
    public void setTitle(String title) { this.title = title; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
}
