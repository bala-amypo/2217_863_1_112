package com.example.demo.service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;

    public CredentialHolderProfileService(CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }

    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        return repository.save(profile);
    }

    public CredentialHolderProfile getHolderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with id " + id));
    }

    public List<CredentialHolderProfile> getAllHolders() {
        return repository.findAll();
    }

    public CredentialHolderProfile findByHolderId(String holderId) {
        return repository.findByHolderId(holderId)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with holderId " + holderId));
    }

    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        CredentialHolderProfile profile = getHolderById(id);
        profile.setActive(active);
        return repository.save(profile);
    }
}
