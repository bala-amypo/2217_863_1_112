package com.example.demo.service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repo;

    public CredentialRecordServiceImpl(CredentialRecordRepository repo) {
        this.repo = repo;
    }

    public CredentialRecord issue(CredentialRecord record) {
        return repo.save(record);
    }

    public List<CredentialRecord> getAll() {
        return repo.findAll();
    }

    public List<CredentialRecord> getByHolderId(Long id) {
        return repo.findByHolderId(id);
    }
}
