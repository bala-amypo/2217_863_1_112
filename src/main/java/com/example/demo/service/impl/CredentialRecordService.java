package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordService {

    private final CredentialRecordRepository repo;

    public CredentialRecordService(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    public CredentialRecord createCredential(CredentialRecord c) {
        if (c.getStatus() == null) c.setStatus("VALID");
        return repo.save(c);
    }

    public CredentialRecord updateCredential(Long id, CredentialRecord c) {
        return repo.save(c);
    }

    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repo.findByHolderId(holderId);
    }

    public CredentialRecord getCredentialByCode(String code) {
        return repo.findByCredentialCode(code);
    }

    public void markExpired(CredentialRecord c) {
        if (c.getExpiryDate() != null && c.getExpiryDate().isBefore(LocalDate.now())) {
            c.setStatus("EXPIRED");
            repo.save(c);
        }
    }
}
