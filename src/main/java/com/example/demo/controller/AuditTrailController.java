package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Audit Trail", description = "View audit logs")
public class AuditTrailController {
    
    private final AuditTrailService auditService;
    
    public AuditTrailController(AuditTrailService auditService) {
        this.auditService = auditService;
    }
    
    @PostMapping
    @Operation(summary = "Log audit event", description = "Create a new audit trail record")
    public ResponseEntity<AuditTrailRecord> log(@RequestBody AuditTrailRecord record) {
        return ResponseEntity.ok(auditService.logEvent(record));
    }
    
    @GetMapping("/credential/{credentialId}")
    @Operation(summary = "Get logs by credential", description = "Get all audit logs for a credential")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(@PathVariable Long credentialId) {
        return ResponseEntity.ok(auditService.getLogsByCredential(credentialId));
    }
    
    @GetMapping
    @Operation(summary = "List all logs", description = "Get all audit trail records")
    public ResponseEntity<List<AuditTrailRecord>> getAll() {
        return ResponseEntity.ok(auditService.getAllLogs());
    }
}