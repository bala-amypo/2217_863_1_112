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

    private final VerificationRequestRepository repository;
    private final CredentialRecordService credentialService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestService(
            VerificationRequestRepository repository,
            CredentialRecordService credentialService,
            AuditTrailService auditTrailService) {

        this.repository = repository;
        this.credentialService = credentialService;
        this.auditTrailService = auditTrailService;
    }

    // Initiate verification
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());
        return repository.save(request);
    }

    // Process verification
    public VerificationRequest processVerification(Long id) {
        VerificationRequest request = getById(id);

        CredentialRecord credential =
                credentialService.getCredentialByCode(request.getCredentialCode());

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDateTime.now().toLocalDate())) {

            request.setStatus("FAILED");
            request.setResultMessage("Credential expired");
        } else {
            request.setStatus("SUCCESS");
            request.setResultMessage("Credential verified successfully");
        }

        request.setVerifiedAt(LocalDateTime.now());

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setEventType("VERIFICATION");
        audit.setEventTime(LocalDateTime.now());
        audit.setDetails(request.getResultMessage());

        auditTrailService.logEvent(audit);

        return repository.save(request);
    }

    // ✅ REQUIRED BY CONTROLLER (FIX)
    public VerificationRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found"));
    }

    // Get requests by credential
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }

    // Get all requests
    public List<VerificationRequest> getAllRequests() {
        return repository.findAll();
    }
}
