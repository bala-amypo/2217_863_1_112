package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;
import java.time.LocalDate;
import java.util.*;

public interface CredentialHolderProfileRepository
        extends JpaRepository<CredentialHolderProfile, Long> {
}
