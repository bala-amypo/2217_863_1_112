package com.example.demo.service;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.repository.VerificationRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestService {

    private final VerificationRequestRepository repository;

    public VerificationRequestService(VerificationRequestRepository repository) {
        this.repository = repository;
    }

    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        request.setVerifiedAt(LocalDateTime.now());
        return repository.save(request);
    }

    public List<VerificationRequest> getAll() {
        return repository.findAll();
    }

    public List<VerificationRequest> getByCredentialId(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }
}
