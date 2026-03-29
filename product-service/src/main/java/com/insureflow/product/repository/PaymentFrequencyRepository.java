package com.insureflow.product.repository;

import com.insureflow.product.entity.PaymentFrequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFrequencyRepository extends JpaRepository<PaymentFrequency, Integer> {
}
