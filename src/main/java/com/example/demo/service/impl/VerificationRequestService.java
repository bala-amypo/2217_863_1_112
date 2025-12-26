package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestService {

    private final VerificationRequestRepository vrRepo;
    private final CredentialRecordService credService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    public VerificationRequestService(
            VerificationRequestRepository vrRepo,
            CredentialRecordService credService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {
        this.vrRepo = vrRepo;
        this.credService = credService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    public VerificationRequest initiateVerification(VerificationRequest r) {
        return vrRepo.save(r);
    }

    public VerificationRequest processVerification(Long id) {
        VerificationRequest req = vrRepo.findById(id).orElseThrow();
        CredentialRecord c = credService.getCredentialByCode(req.getCredentialId().toString());

        if (c.getExpiryDate() != null && c.getExpiryDate().isBefore(java.time.LocalDate.now())) {
            req.setStatus("FAILED");
        } else {
            req.setStatus("SUCCESS");
        }

        req.setVerifiedAt(LocalDateTime.now());
        auditService.logEvent(new AuditTrailRecord());
        return vrRepo.save(req);
    }

    public List<VerificationRequest> getRequestsByCredential(Long id) {
        return vrRepo.findByCredentialId(id);
    }
}
