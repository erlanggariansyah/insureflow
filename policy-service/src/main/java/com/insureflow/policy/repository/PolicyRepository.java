package com.insureflow.policy.repository;

import com.insureflow.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {
}
