package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "verification_rules")
public class VerificationRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleCode;
    
    private String description;
    
    private String appliesToType;
    
    private String validationExpression;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToMany(mappedBy = "rules")
    private Set<CredentialRecord> credentials = new HashSet<>();
    
    public VerificationRule() {}
    
    public VerificationRule(String ruleCode, String description, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.active = active != null ? active : true;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getAppliesToType() { return appliesToType; }
    public void setAppliesToType(String appliesToType) { this.appliesToType = appliesToType; }
    
    public String getValidationExpression() { return validationExpression; }
    public void setValidationExpression(String validationExpression) { 
        this.validationExpression = validationExpression; 
    }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<CredentialRecord> getCredentials() { return credentials; }
    public void setCredentials(Set<CredentialRecord> credentials) { this.credentials = credentials; }
}
