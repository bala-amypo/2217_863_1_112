package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleService(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    public VerificationRule create(VerificationRule rule) {
        return repository.save(rule);
    }

    public List<VerificationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
