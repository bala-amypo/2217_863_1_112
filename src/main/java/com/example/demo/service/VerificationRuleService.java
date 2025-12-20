package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleService(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    public VerificationRule createRule(VerificationRule rule) {
        return repository.save(rule);
    }

    public VerificationRule updateRule(Long id, VerificationRule updated) {
        VerificationRule existing = getById(id);
        updated.setId(existing.getId());
        return repository.save(updated);
    }

    public VerificationRule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id " + id));
    }

    public List<VerificationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    public List<VerificationRule> getAllRules() {
        return repository.findAll();
    }
}
