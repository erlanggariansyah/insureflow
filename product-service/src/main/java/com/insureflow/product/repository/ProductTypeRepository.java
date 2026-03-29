package com.insureflow.product.repository;

import com.insureflow.product.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {
}
