package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification")
public class VerificationRequestController {

    private final VerificationRequestService service;

    public VerificationRequestController(VerificationRequestService service) {
        this.service = service;
    }

    @PostMapping
    public VerificationRequest requestVerification(
            @RequestBody VerificationRequest request) {
        return service.initiateVerification(request);
    }

    @GetMapping
    public List<VerificationRequest> getAll() {
        return service.getAll();
    }

    @GetMapping("/credential/{credentialId}")
    public List<VerificationRequest> getByCredential(
            @PathVariable Long credentialId) {
        return service.getByCredentialId(credentialId);
    }
}
