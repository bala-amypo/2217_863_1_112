package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailService {

    private final AuditTrailRecordRepository repository;

    public AuditTrailService(AuditTrailRecordRepository repository) {
        this.repository = repository;
    }

    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        return repository.save(record);
    }

    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }

    public List<AuditTrailRecord> getAllLogs() {
        return repository.findAll();
    }
}
