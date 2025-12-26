package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    @PostMapping
    public CredentialRecord createCredential(
            @RequestBody CredentialRecord record) {
        return service.createCredential(record);
    }

    @PutMapping("/{id}")
    public CredentialRecord updateCredential(
            @PathVariable Long id,
            @RequestBody CredentialRecord record) {
        return service.updateCredential(id, record);
    }

    @GetMapping("/holder/{holderId}")
    public List<CredentialRecord> getByHolder(@PathVariable Long holderId) {
        return service.getCredentialsByHolder(holderId);
    }

    @GetMapping("/code/{credentialCode}")
    public CredentialRecord getByCode(@PathVariable String credentialCode) {
        return service.getCredentialByCode(credentialCode);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getCredentialsByHolder(null);
    }
}
