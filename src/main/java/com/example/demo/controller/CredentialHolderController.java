package com.example.demo.controller;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holders")
public class CredentialHolderController {

    private final CredentialHolderProfileService service;

    public CredentialHolderController(CredentialHolderProfileService service) {
        this.service = service;
    }

    @PostMapping
    public CredentialHolderProfile create(@RequestBody CredentialHolderProfile profile) {
        return service.createHolder(profile);
    }

    @GetMapping("/{id}")
    public CredentialHolderProfile getById(@PathVariable Long id) {
        return service.getHolderById(id);
    }

    @GetMapping
    public List<CredentialHolderProfile> getAll() {
        return service.getAllHolders();
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        service.updateHolderStatus(id, active);
    }
}
