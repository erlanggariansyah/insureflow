package com.openlife.product.repository;

import com.openlife.product.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {
}
