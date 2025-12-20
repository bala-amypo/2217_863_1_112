package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "verification_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;

    private String description;

    private String appliesToType;

    @Column(columnDefinition = "TEXT")
    private String validationExpression;

    private Boolean active;

    public VerificationRule() {}
}
