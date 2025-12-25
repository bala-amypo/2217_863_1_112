package com.example.demo.service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;

    public CredentialHolderProfileService(CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }

    public CredentialHolderProfile create(CredentialHolderProfile profile) {
        return repository.save(profile);
    }

    public CredentialHolderProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holder not found"));
    }

    public CredentialHolderProfile updateStatus(Long id, boolean active) {
        CredentialHolderProfile holder = getById(id);
        holder.setActive(active);
        return repository.save(holder);
    }

    public List<CredentialHolderProfile> getAll() {
        return repository.findAll();
    }
}
