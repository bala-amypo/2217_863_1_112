package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditTrailService {

    private final AuditTrailRecordRepository repository;

    public AuditTrailService(AuditTrailRecordRepository repository) {
        this.repository = repository;
    }

    public AuditTrailRecord log(AuditTrailRecord record) {
        record.setLoggedAt(LocalDateTime.now());
        return repository.save(record);
    }

    public List<AuditTrailRecord> getByCredentialId(Long credentialId) {
        return repository.findByCredentialId(credentialId);
    }
}
