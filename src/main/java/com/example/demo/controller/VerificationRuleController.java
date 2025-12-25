package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Verification Rules", description = "Manage verification rules")
public class VerificationRuleController {
    
    private final VerificationRuleService ruleService;
    
    public VerificationRuleController(VerificationRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    @Operation(summary = "Create rule", description = "Create a new verification rule")
    public ResponseEntity<VerificationRule> create(@RequestBody VerificationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update rule", description = "Update an existing verification rule")
    public ResponseEntity<VerificationRule> update(@PathVariable Long id, 
                                                   @RequestBody VerificationRule updatedRule) {
        return ResponseEntity.ok(ruleService.updateRule(id, updatedRule));
    }
    
    @GetMapping("/active")
    @Operation(summary = "List active rules", description = "Get all active verification rules")
    public ResponseEntity<List<VerificationRule>> getActive() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
    
    @GetMapping
    @Operation(summary = "List all rules", description = "Get all verification rules")
    public ResponseEntity<List<VerificationRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}