package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

// ❌ @RestController   <-- MUST BE GONE
// ❌ @RequestMapping("/api/audit")  <-- MUST BE GONE

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Audit Trail", description = "View audit logs")
public class AuditTrailController {

    private final AuditTrailService auditService;

    public AuditTrailController(AuditTrailService auditService) {
        this.auditService = auditService;
    }

    @Operation(summary = "Log audit event")
    public ResponseEntity<AuditTrailRecord> log(AuditTrailRecord record) {
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Get logs by credential")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(Long credentialId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "List all logs")
    public ResponseEntity<List<AuditTrailRecord>> getAll() {
        return ResponseEntity.ok(List.of());
    }
}
