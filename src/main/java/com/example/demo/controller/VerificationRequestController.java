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
    public VerificationRequest initiateVerification(
            @RequestBody VerificationRequest request) {
        return service.initiateVerification(request);
    }

    @PutMapping("/{id}/process")
    public VerificationRequest processVerification(
            @PathVariable Long id) {
        return service.processVerification(id);
    }

    @GetMapping("/credential/{credentialId}")
    public List<VerificationRequest> getByCredential(
            @PathVariable Long credentialId) {
        return service.getRequestsByCredential(credentialId);
    }

    @GetMapping("/{id}")
    public VerificationRequest getById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public List<VerificationRequest> getAll() {
        return service.getAllRequests();
    }
}
