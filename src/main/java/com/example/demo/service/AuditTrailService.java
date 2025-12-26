package com.example.demo.service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditTrailService {

    private final AuditTrailRecordRepository repo;

    public AuditTrailService(AuditTrailRecordRepository repo) {
        this.repo = repo;
    }

    public void logEvent(AuditTrailRecord r) {
        repo.save(r);
    }

    public List<AuditTrailRecord> getLogsByCredential(Long id) {
        return repo.findByCredentialId(id);
    }

    public List<AuditTrailRecord> getAllLogs() {
        return repo.findAll();
    }
}
