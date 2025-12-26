package com.example.demo.controller;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class VerificationRuleController {

    private final VerificationRuleService service;

    public VerificationRuleController(VerificationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public VerificationRule createRule(@RequestBody VerificationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public VerificationRule updateRule(
            @PathVariable Long id,
            @RequestBody VerificationRule rule) {
        return service.createRule(rule);
    }

    @GetMapping("/active")
    public List<VerificationRule> getActiveRules() {
        return service.getActiveRules();
    }

    @GetMapping
    public List<VerificationRule> getAllRules() {
        return service.getAllRules();
    }

    @GetMapping("/{id}")
    public VerificationRule getRuleById(@PathVariable Long id) {
        return null;
    }
}
