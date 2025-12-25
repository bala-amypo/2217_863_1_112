package com.example.demo.controller;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/holders")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Credential Holders", description = "Manage credential holder profiles")
public class CredentialHolderController {
    
    private final CredentialHolderProfileService holderService;
    
    public CredentialHolderController(CredentialHolderProfileService holderService) {
        this.holderService = holderService;
    }
    
    @PostMapping
    @Operation(summary = "Create holder", description = "Create a new credential holder profile")
    public ResponseEntity<CredentialHolderProfile> create(@RequestBody CredentialHolderProfile profile) {
        return ResponseEntity.ok(holderService.createHolder(profile));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get holder by ID", description = "Retrieve a holder profile by database ID")
    public ResponseEntity<CredentialHolderProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(holderService.getHolderById(id));
    }
    
    @GetMapping
    @Operation(summary = "List all holders", description = "Get all credential holder profiles")
    public ResponseEntity<List<CredentialHolderProfile>> getAll() {
        return ResponseEntity.ok(holderService.getAllHolders());
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update holder status", description = "Update the active status of a holder")
    public ResponseEntity<CredentialHolderProfile> updateStatus(@PathVariable Long id, 
                                                                @RequestParam boolean active) {
        return ResponseEntity.ok(holderService.updateHolderStatus(id, active));
    }
}
