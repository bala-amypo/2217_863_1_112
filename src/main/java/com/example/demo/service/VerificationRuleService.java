package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VerificationRuleService {

    private final VerificationRuleRepository repo;

    public VerificationRuleService(VerificationRuleRepository repo) {
        this.repo = repo;
    }

    public VerificationRule createRule(VerificationRule rule) {
        return repo.save(rule);
    }

    public List<VerificationRule> getActiveRules() {
        return repo.findAll()
                .stream()
                .filter(VerificationRule::getActive)
                .collect(Collectors.toList());
    }

    public List<VerificationRule> getAllRules() {
        return repo.findAll();
    }
}
