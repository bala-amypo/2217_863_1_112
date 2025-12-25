package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordService(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    public CredentialRecord issue(CredentialRecord record) {
        return repository.save(record);
    }

    public List<CredentialRecord> getAll() {
        return repository.findAll();
    }

    public List<CredentialRecord> getByHolderId(Long holderId) {
        return repository.findByHolderId(holderId);
    }
}
