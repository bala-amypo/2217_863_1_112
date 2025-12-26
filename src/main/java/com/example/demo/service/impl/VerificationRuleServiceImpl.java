package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {
    
    private final VerificationRuleRepository ruleRepository;
    
    public VerificationRuleServiceImpl(VerificationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
    
    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return ruleRepository.save(rule);
    }
    
    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        VerificationRule existing = ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
        
        if (updatedRule.getRuleCode() != null) {
            existing.setRuleCode(updatedRule.getRuleCode());
        }
        if (updatedRule.getDescription() != null) {
            existing.setDescription(updatedRule.getDescription());
        }
        if (updatedRule.getActive() != null) {
            existing.setActive(updatedRule.getActive());
        }
        
        return ruleRepository.save(existing);
    }
    
    @Override
    public List<VerificationRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }
    
    @Override
    public List<VerificationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}