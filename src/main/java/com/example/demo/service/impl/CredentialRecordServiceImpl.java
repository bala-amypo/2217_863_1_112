package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {
    
    private final CredentialRecordRepository credentialRepository;
    
    public CredentialRecordServiceImpl(CredentialRecordRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }
    
    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getExpiryDate() != null && record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        } else if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return credentialRepository.save(record);
    }
    
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = credentialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with id: " + id));
        
        if (updated.getCredentialCode() != null) {
            existing.setCredentialCode(updated.getCredentialCode());
        }
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getIssuer() != null) {
            existing.setIssuer(updated.getIssuer());
        }
        if (updated.getCredentialType() != null) {
            existing.setCredentialType(updated.getCredentialType());
        }
        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }
        if (updated.getExpiryDate() != null) {
            existing.setExpiryDate(updated.getExpiryDate());
        }
        if (updated.getMetadataJson() != null) {
            existing.setMetadataJson(updated.getMetadataJson());
        }
        
        if (existing.getExpiryDate() != null && existing.getExpiryDate().isBefore(LocalDate.now())) {
            existing.setStatus("EXPIRED");
        }
        
        return credentialRepository.save(existing);
    }
    
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return credentialRepository.findByHolderId(holderId);
    }
    
    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return credentialRepository.findByCredentialCode(code).orElse(null);
    }
    
    @Override
    public List<CredentialRecord> getAllCredentials() {
        return credentialRepository.findAll();
    }
}