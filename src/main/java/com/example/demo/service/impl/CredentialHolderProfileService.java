package com.example.demo.service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;

    public CredentialHolderProfileService(CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }

    public CredentialHolderProfile create(CredentialHolderProfile holder) {
        holder.setActive(true);
        return repository.save(holder);
    }

    public CredentialHolderProfile getByHolderId(String holderId) {
        return repository.findByHolderId(holderId)
                .orElseThrow(() -> new RuntimeException("Holder not found"));
    }
}
