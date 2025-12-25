package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);
}
