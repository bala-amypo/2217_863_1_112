package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
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

    public CredentialRecord create(CredentialRecord credential) {
        credential.setStatus("ACTIVE");
        return repository.save(credential);
    }

    public CredentialRecord update(Long id, CredentialRecord updated) {
        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));

        existing.setTitle(updated.getTitle());
        existing.setIssuer(updated.getIssuer());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setMetadataJson(updated.getMetadataJson());

        return repository.save(existing);
    }

    public List<CredentialRecord> getByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    public CredentialRecord getByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }

    public List<CredentialRecord> findExpiredBefore(LocalDate date) {
        return repository.findExpiredBefore(date);
    }
}
