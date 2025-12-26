package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CredentialHolderProfileServiceImpl implements CredentialHolderProfileService {
    
    private final CredentialHolderProfileRepository holderRepository;
    
    public CredentialHolderProfileServiceImpl(CredentialHolderProfileRepository holderRepository) {
        this.holderRepository = holderRepository;
    }
    
    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        return holderRepository.save(profile);
    }
    
    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        return holderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with id: " + id));
    }
    
    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        return holderRepository.findAll();
    }
    
    @Override
    public CredentialHolderProfile findByHolderId(String holderId) {
        return holderRepository.findByHolderId(holderId)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with holderId: " + holderId));
    }
    
    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        CredentialHolderProfile profile = getHolderById(id);
        profile.setActive(active);
        return holderRepository.save(profile);
    }
}