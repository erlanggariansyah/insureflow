package com.insureflow.product.repository;

import com.insureflow.product.entity.ProductRider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRiderRepository extends JpaRepository<ProductRider, UUID> {
    List<ProductRider> findByProductId(UUID productId);
}
