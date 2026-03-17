package com.insureflow.risk.repository;

import com.insureflow.risk.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiskRepository extends JpaRepository<Risk, UUID> {
    Optional<Risk> findByPolicyId(UUID policyId);
}
