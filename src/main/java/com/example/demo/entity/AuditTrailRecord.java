package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private String eventType;

    private LocalDateTime loggedAt;

    @PrePersist
    public void prePersist() {
        loggedAt = LocalDateTime.now();
    }

    public Long getCredentialId() { return credentialId; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}
