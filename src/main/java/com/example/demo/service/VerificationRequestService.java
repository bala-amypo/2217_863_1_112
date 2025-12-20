package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestService {

    private final VerificationRequestRepository requestRepository;
    private final CredentialRecordService credentialRecordService;
    private final VerificationRuleService verificationRuleService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestService(
            VerificationRequestRepository requestRepository,
            CredentialRecordService credentialRecordService,
            VerificationRuleService verificationRuleService,
            AuditTrailService auditTrailService) {

        this.requestRepository = requestRepository;
        this.credentialRecordService = credentialRecordService;
        this.verificationRuleService = verificationRuleService;
        this.auditTrailService = auditTrailService;
    }

    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepository.save(request);
    }

    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found"));

        CredentialRecord credential =
                credentialRecordService.getById(request.getCredentialId());

        if (credential.getExpiryDate().isBefore(java.time.LocalDate.now())) {
            request.setStatus("FAILED");
            request.setResultMessage("Credential expired");
        } else {
            request.setStatus("SUCCESS");
            request.setResultMessage("Credential valid");
        }

        request.setVerifiedAt(LocalDateTime.now());
        VerificationRequest saved = requestRepository.save(request);

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setEventType("VERIFICATION");
        audit.setDetails(request.getResultMessage());

        auditTrailService.logEvent(audit);

        return saved;
    }

    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepository.findByCredentialId(credentialId);
    }

    public List<VerificationRequest> getAllRequests() {
        return requestRepository.findAll();
    }
}
