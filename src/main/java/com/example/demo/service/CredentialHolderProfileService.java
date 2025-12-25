package com.example.demo.service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;

    public CredentialHolderProfileService(CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }

    public CredentialHolderProfile create(CredentialHolderProfile profile) {
        profile.setActive(true);
        return repository.save(profile);
    }

    public CredentialHolderProfile getByHolderId(String holderId) {
        return repository.findByHolderId(holderId)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found"));
    }
}
