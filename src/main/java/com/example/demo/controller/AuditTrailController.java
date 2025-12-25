package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }

    @PostMapping
    public AuditTrailRecord log(@RequestBody AuditTrailRecord record) {
        return service.logEvent(record);
    }

    @GetMapping("/credential/{credentialId}")
    public List<AuditTrailRecord> getByCredential(@PathVariable Long credentialId) {
        return service.getLogsByCredential(credentialId);
    }

    @GetMapping
    public List<AuditTrailRecord> getAll() {
        return service.getAllLogs();
    }
}
