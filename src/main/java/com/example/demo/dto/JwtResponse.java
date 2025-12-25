package com.example.demo.dto;

public class JwtResponse {

    private String token;

    public JwtResponse() {}

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
UserService.java
│   ├── CredentialRecordService.java
│   ├── CredentialHolderProfileService.java
│   ├── VerificationRequestService.java
│   ├── VerificationRuleService.java
│   └── AuditTrailService.java