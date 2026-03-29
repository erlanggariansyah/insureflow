package com.insureflow.product.repository;

import com.insureflow.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByCode(String code);
    List<Product> findByProductTypeIdAndIsActiveTrue(UUID productTypeId);
}
