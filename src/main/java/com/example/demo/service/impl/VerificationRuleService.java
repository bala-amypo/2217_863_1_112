package com.example.demo.service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.repository.VerificationRuleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VerificationRuleService {

    private final VerificationRuleRepository repo;

    public VerificationRuleService(VerificationRuleRepository repo) {
        this.repo = repo;
    }

    public VerificationRule createRule(VerificationRule r) {
        return repo.save(r);
    }

    public List<VerificationRule> getActiveRules() {
        return repo.findAll().stream().filter(VerificationRule::getActive).toList();
    }

    public List<VerificationRule> getAllRules() {
        return repo.findAll();
    }
}
