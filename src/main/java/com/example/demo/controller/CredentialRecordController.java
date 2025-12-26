package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/credentials")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Credentials", description = "Manage credential records")
public class CredentialRecordController {
    
    private final CredentialRecordService credentialService;
    
    public CredentialRecordController(CredentialRecordService credentialService) {
        this.credentialService = credentialService;
    }
    
    @PostMapping
    @Operation(summary = "Create credential", description = "Create a new credential record")
    public ResponseEntity<CredentialRecord> create(@RequestBody CredentialRecord record) {
        return ResponseEntity.ok(credentialService.createCredential(record));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update credential", description = "Update an existing credential")
    public ResponseEntity<CredentialRecord> update(@PathVariable Long id, 
                                                   @RequestBody CredentialRecord updated) {
        return ResponseEntity.ok(credentialService.updateCredential(id, updated));
    }
    
    @GetMapping("/holder/{holderId}")
    @Operation(summary = "Get credentials by holder", description = "Get all credentials for a specific holder")
    public ResponseEntity<List<CredentialRecord>> getByHolder(@PathVariable Long holderId) {
        return ResponseEntity.ok(credentialService.getCredentialsByHolder(holderId));
    }
    
    @GetMapping("/code/{credentialCode}")
    @Operation(summary = "Find credential by code", description = "Find a credential by its unique code")
    public ResponseEntity<CredentialRecord> getByCode(@PathVariable String credentialCode) {
        CredentialRecord record = credentialService.getCredentialByCode(credentialCode);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }
    
    @GetMapping
    @Operation(summary = "List all credentials", description = "Get all credential records")
    public ResponseEntity<List<CredentialRecord>> getAll() {
        return ResponseEntity.ok(credentialService.getAllCredentials());
    }
}
