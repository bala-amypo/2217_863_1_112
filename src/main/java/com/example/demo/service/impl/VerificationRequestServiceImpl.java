package com.example.demo.service.impl;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service   // ✅ Spring will create the bean automatically
public class VerificationRequestServiceImpl implements VerificationRequestService {

    // Temporary in-memory storage (replace with DB later)
    private final List<VerificationRequest> store = new ArrayList<>();

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        store.add(request);
        return request;
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {
        return store.stream()
                .filter(r -> r.getId() != null && r.getId().equals(requestId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        List<VerificationRequest> result = new ArrayList<>();
        for (VerificationRequest req : store) {
            if (req.getCredentialId() != null
                    && req.getCredentialId().equals(credentialId)) {
                result.add(req);
            }
        }
        return result;
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return store;
    }
}
