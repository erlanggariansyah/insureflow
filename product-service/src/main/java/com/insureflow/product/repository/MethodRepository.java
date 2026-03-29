package com.insureflow.product.repository;

import com.insureflow.product.entity.Method;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodRepository extends JpaRepository<Method, Integer> {
}
