package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {
    
    private final VerificationRequestRepository verificationRequestRepository;
    private final CredentialRecordService credentialRecordService;
    private final VerificationRuleService verificationRuleService;
    private final AuditTrailService auditTrailService;
    
    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRequestRepository,
            CredentialRecordService credentialRecordService,
            VerificationRuleService verificationRuleService,
            AuditTrailService auditTrailService) {
        this.verificationRequestRepository = verificationRequestRepository;
        this.credentialRecordService = credentialRecordService;
        this.verificationRuleService = verificationRuleService;
        this.auditTrailService = auditTrailService;
    }
    
    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return verificationRequestRepository.save(request);
    }
    
    @Override
    public VerificationRequest processVerification(Long requestId) {
        VerificationRequest request = verificationRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found with id: " + requestId));
        
        CredentialRecord credential = credentialRecordService.getAllCredentials().stream()
                .filter(c -> c.getId().equals(request.getCredentialId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
        
        List<VerificationRule> activeRules = verificationRuleService.getActiveRules();
        
        if (credential.getExpiryDate() != null && credential.getExpiryDate().isBefore(LocalDate.now())) {
            request.setStatus("FAILED");
            request.setResultMessage("Credential has expired");
        } else {
            request.setStatus("SUCCESS");
            request.setResultMessage("Verification successful");
        }
        
        request.setVerifiedAt(LocalDateTime.now());
        
        AuditTrailRecord auditRecord = new AuditTrailRecord(
                credential.getId(),
                "VERIFICATION",
                "Verification request processed with status: " + request.getStatus()
        );
        auditTrailService.logEvent(auditRecord);
        
        return verificationRequestRepository.save(request);
    }
    
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return verificationRequestRepository.findByCredentialId(credentialId);
    }
    
    @Override
    public List<VerificationRequest> getAllRequests() {
        return verificationRequestRepository.findAll();
    }
}