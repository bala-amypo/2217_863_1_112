package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private String description;
    private String appliesToType;
    private String validationExpression;
    private Boolean active = true;

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public Boolean getActive() { return active; }
}
