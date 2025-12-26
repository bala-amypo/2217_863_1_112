package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ Spring will auto-detect this as a bean
public class VerificationRuleServiceImpl implements VerificationRuleService {

    // Temporary in-memory storage (replace with DB later)
    private final List<VerificationRule> store = new ArrayList<>();

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        store.add(rule);
        return rule;
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        for (int i = 0; i < store.size(); i++) {
            VerificationRule existing = store.get(i);
            if (existing.getId() != null && existing.getId().equals(id)) {
                store.set(i, updatedRule);
                return updatedRule;
            }
        }
        return null;
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        List<VerificationRule> activeRules = new ArrayList<>();
        for (VerificationRule rule : store) {
            if (rule.isActive()) {
                activeRules.add(rule);
            }
        }
        return activeRules;
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return store;
    }
}
