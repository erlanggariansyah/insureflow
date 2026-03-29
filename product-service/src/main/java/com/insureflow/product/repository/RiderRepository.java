package com.insureflow.product.repository;

import com.insureflow.product.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiderRepository extends JpaRepository<Rider, UUID> {
    Optional<Rider> findByCode(String code);
    Optional<Rider> findByIdAndIsActiveTrue(UUID id);
}
