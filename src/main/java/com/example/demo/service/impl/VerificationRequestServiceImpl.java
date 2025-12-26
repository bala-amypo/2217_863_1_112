package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl {

    private final VerificationRequestRepository repo;
    private final CredentialRecordServiceImpl credentialService;
    private final AuditTrailServiceImpl auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repo,
            CredentialRecordServiceImpl credentialService,
            AuditTrailServiceImpl auditService) {

        this.repo = repo;
        this.credentialService = credentialService;
        this.auditService = auditService;
    }

    public VerificationRequest initiateVerification(VerificationRequest r) {
        return repo.save(r);
    }

    public VerificationRequest processVerification(Long id) {
        VerificationRequest req = repo.findById(id).orElseThrow();
        CredentialRecord record =
                credentialService.getCredentialByCode(
                        req.getCredentialId().toString());

        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(java.time.LocalDate.now())) {
            req.setStatus("FAILED");
        } else {
            req.setStatus("SUCCESS");
        }

        req.setVerifiedAt(LocalDateTime.now());
        auditService.logEvent(new AuditTrailRecord());

        return repo.save(req);
    }

    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return repo.findByCredentialId(credentialId);
    }

    public List<VerificationRequest> getAllRequests() {
        return repo.findAll();
    }
}
