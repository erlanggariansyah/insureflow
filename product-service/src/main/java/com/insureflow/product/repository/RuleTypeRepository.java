package com.insureflow.product.repository;

import com.insureflow.product.entity.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuleTypeRepository extends JpaRepository<RuleType, UUID> {
}
