package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "credential_holder_profiles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "holderId"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderId;

    private String fullName;

    private String email;

    private String organization;

    private Boolean active;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "holderId")
    private List<CredentialRecord> credentials;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (active == null) active = true;
    }

    public CredentialHolderProfile() {}
}
