package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordService(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    // Create credential
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return repository.save(record);
    }

    // Update credential (NO setId usage)
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        existing.setCredentialCode(updated.getCredentialCode());
        existing.setCredentialType(updated.getCredentialType());
        existing.setIssuer(updated.getIssuer());
        existing.setIssueDate(updated.getIssueDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());
        existing.setMetadataJson(updated.getMetadataJson());
        existing.setHolderProfile(updated.getHolderProfile());

        return repository.save(existing);
    }

    // Get all credentials
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    // Get credentials by holder
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderProfileId(holderId);
    }

   
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    }
}
