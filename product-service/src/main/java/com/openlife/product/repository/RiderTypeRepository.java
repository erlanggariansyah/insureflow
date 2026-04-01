package com.openlife.product.repository;

import com.openlife.product.entity.RiderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiderTypeRepository extends JpaRepository<RiderType, UUID> {
}
