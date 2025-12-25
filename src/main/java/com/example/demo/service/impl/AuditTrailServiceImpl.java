package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        return record; // temporary no-op
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return List.of();
    }

    @Override
    public List<AuditTrailRecord> getAllLogs() {
        return List.of();
    }
}
