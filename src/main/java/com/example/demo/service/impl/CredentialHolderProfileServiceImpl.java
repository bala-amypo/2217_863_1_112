package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialHolderProfileServiceImpl {

    private final CredentialHolderProfileRepository repo;

    public CredentialHolderProfileServiceImpl(CredentialHolderProfileRepository repo) {
        this.repo = repo;
    }

    public CredentialHolderProfile createHolder(CredentialHolderProfile p) {
        return repo.save(p);
    }

    public CredentialHolderProfile getHolderById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found"));
    }

    public List<CredentialHolderProfile> getAllHolders() {
        return repo.findAll();
    }

    public void updateHolderStatus(Long id, boolean active) {
        CredentialHolderProfile p = getHolderById(id);
        p.setActive(active);
        repo.save(p);
    }
}
