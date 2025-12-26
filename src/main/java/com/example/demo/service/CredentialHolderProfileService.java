package com.example.demo.service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repo;

    public CredentialHolderProfileService(CredentialHolderProfileRepository repo) {
        this.repo = repo;
    }

    public CredentialHolderProfile createHolder(CredentialHolderProfile p) {
        return repo.save(p);
    }

    public CredentialHolderProfile getHolderById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
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
