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
    private String details;
    private LocalDateTime loggedAt;

    @PrePersist
    void onCreate() {
        loggedAt = LocalDateTime.now();
    }

    public LocalDateTime getLoggedAt() { return loggedAt; }
}
