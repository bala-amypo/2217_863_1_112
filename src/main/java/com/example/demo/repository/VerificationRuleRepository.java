package com.example.demo.repository;

import com.example.demo.entity.VerificationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationRuleRepository
        extends JpaRepository<VerificationRule, Long> {

    Optional<VerificationRule> findByRuleCode(String ruleCode);

    List<VerificationRule> findByActiveTrue();
}
