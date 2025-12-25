package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_trail_records")
public class AuditTrailRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long credentialId;
    
    private String eventType;
    
    private String details;
    
    @Column(nullable = false)
    private LocalDateTime loggedAt;
    
    @PrePersist
    protected void onCreate() {
        if (loggedAt == null) {
            loggedAt = LocalDateTime.now();
        }
    }
    
    public AuditTrailRecord() {}
    
    public AuditTrailRecord(Long credentialId, String eventType, String details) {
        this.credentialId = credentialId;
        this.eventType = eventType;
        this.details = details;
        this.loggedAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCredentialId() { return credentialId; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    
    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}