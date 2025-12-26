package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ Creates Spring bean automatically
public class CredentialHolderProfileServiceImpl
        implements CredentialHolderProfileService {

    // Temporary in-memory storage (replace with DB later)
    private final List<CredentialHolderProfile> store = new ArrayList<>();

    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        store.add(profile);
        return profile;
    }

    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        return store.stream()
                .filter(h -> h.getId() != null && h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        return store;
    }

    @Override
    public CredentialHolderProfile findByHolderId(String holderId) {
        return store.stream()
                .filter(h -> h.getHolderId() != null
                        && h.getHolderId().equals(holderId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        CredentialHolderProfile holder = getHolderById(id);
        if (holder != null) {
            holder.setActive(active);
        }
        return holder;
    }
}
