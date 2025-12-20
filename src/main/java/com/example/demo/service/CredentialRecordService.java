package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordService(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return repository.save(record);
    }

    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = getById(id);
        updated.setId(existing.getId());
        return repository.save(updated);
    }

    public CredentialRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with id " + id));
    }

    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    public CredentialRecord getByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with code " + code));
    }

    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    public List<CredentialRecord> findExpired(LocalDate date) {
        return repository.findExpiredBefore(date);
    }
}
