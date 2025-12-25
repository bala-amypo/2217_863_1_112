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
        return service.create(rule);
    }

    @GetMapping
    public List<VerificationRule> getAll() {
        return service.getAll();
    }

    @GetMapping("/active")
    public List<VerificationRule> getActiveRules() {
        return service.getActiveRules();
    }
}
