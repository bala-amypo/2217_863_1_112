package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VerificationRuleServiceImpl {

    private final VerificationRuleRepository repo;

    public VerificationRuleServiceImpl(VerificationRuleRepository repo) {
        this.repo = repo;
    }

    // Create or update rule
    public VerificationRule createRule(VerificationRule rule) {
        return repo.save(rule);
    }

    // Get all rules
    public List<VerificationRule> getAllRules() {
        return repo.findAll();
    }

    // Get only active rules
    public List<VerificationRule> getActiveRules() {
        return repo.findAll()
                .stream()
                .filter(r -> Boolean.TRUE.equals(r.getActive()))
                .collect(Collectors.toList());
    }

    // Get rule by id
    public VerificationRule getRuleById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
