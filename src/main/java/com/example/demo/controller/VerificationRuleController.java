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
    public VerificationRule create(@RequestBody VerificationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public VerificationRule update(@PathVariable Long id, @RequestBody VerificationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<VerificationRule> getActive() {
        return service.getActiveRules();
    }

    @GetMapping("/{id}")
    public VerificationRule getById(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<VerificationRule> getAll() {
        return service.getAllRules();
    }
}
