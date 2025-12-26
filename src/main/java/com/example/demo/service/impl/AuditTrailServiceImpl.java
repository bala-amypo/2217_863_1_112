package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ This creates the Spring bean
public class AuditTrailServiceImpl implements AuditTrailService {

    // Temporary in-memory storage (replace with DB later)
    private final List<AuditTrailRecord> store = new ArrayList<>();

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        store.add(record);
        return record;
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return store.stream()
                .filter(r -> r.getCredentialId() != null
                        && r.getCredentialId().equals(credentialId))
                .toList();
    }

    @Override
    public List<AuditTrailRecord> getAllLogs() {
        return store;
    }
}
