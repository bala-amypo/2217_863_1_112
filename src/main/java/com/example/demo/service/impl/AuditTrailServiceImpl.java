package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailServiceImpl {

    private final AuditTrailRecordRepository repo;

    public AuditTrailServiceImpl(AuditTrailRecordRepository repo) {
        this.repo = repo;
    }

    public void logEvent(AuditTrailRecord record) {
        repo.save(record);
    }

    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return repo.findByCredentialId(credentialId);
    }

    public List<AuditTrailRecord> getAllLogs() {
        return repo.findAll();
    }
}
