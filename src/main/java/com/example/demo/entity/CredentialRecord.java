package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(
        name = "credential_records",
        uniqueConstraints = @UniqueConstraint(columnNames = "credentialCode")
)
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

    @ManyToMany
    @JoinTable(
            name = "credential_rule_map",
            joinColumns = @JoinColumn(name = "credentialId"),
            inverseJoinColumns = @JoinColumn(name = "ruleId")
    )
    private Set<VerificationRule> rules;

    public CredentialRecord() {}
}
