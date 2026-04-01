package com.openlife.product.repository;

import com.openlife.product.entity.PaymentFrequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFrequencyRepository extends JpaRepository<PaymentFrequency, Integer> {
}
