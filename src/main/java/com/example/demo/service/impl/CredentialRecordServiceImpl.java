package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl {

    private final CredentialRecordRepository repo;

    public CredentialRecordServiceImpl(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return repo.save(record);
    }

    public CredentialRecord updateCredential(Long id, CredentialRecord record) {
        return repo.save(record);
    }

    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repo.findByHolderId(holderId);
    }

    public CredentialRecord getCredentialByCode(String code) {
        return repo.findByCredentialCode(code);
    }

    public void markExpiredIfNeeded(CredentialRecord r) {
        if (r.getExpiryDate() != null &&
            r.getExpiryDate().isBefore(LocalDate.now())) {
            r.setStatus("EXPIRED");
            repo.save(r);
        }
    }
}
