package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ Spring will create the bean automatically
public class CredentialRecordServiceImpl implements CredentialRecordService {

    // Temporary in-memory storage (replace with DB later)
    private final List<CredentialRecord> store = new ArrayList<>();

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        store.add(record);
        return record;
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        for (int i = 0; i < store.size(); i++) {
            CredentialRecord existing = store.get(i);
            if (existing.getId() != null && existing.getId().equals(id)) {
                store.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        List<CredentialRecord> result = new ArrayList<>();
        for (CredentialRecord record : store) {
            if (record.getHolderId() != null
                    && record.getHolderId().equals(holderId)) {
                result.add(record);
            }
        }
        return result;
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return store.stream()
                .filter(r -> r.getCode() != null
                        && r.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return store;
    }
}
