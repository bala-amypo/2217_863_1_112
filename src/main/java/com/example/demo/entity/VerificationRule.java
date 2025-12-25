package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private boolean active;

    public Long getId() { return id; }
    public String getRuleCode() { return ruleCode; }
    public boolean isActive() { return active; }

    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public void setActive(boolean active) { this.active = active; }
}
