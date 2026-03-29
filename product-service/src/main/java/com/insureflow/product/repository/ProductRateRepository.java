package com.insureflow.product.repository;

import com.insureflow.product.constant.AttributeConstant;
import com.insureflow.product.entity.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRateRepository extends JpaRepository<ProductRate, UUID> {
    @Query(value = """
    SELECT pr.rate FROM product_rates pr
    WHERE pr.product_id = :productId
      AND pr.is_active = true
      AND (pr.gender = :gender OR pr.gender IS NULL)
      AND pr.age <= :age
    ORDER BY
        pr.age DESC,
        pr.gender DESC,
        pr.updated_at DESC
    LIMIT 1
    """, nativeQuery = true)
    BigDecimal findRate(@Param(AttributeConstant.PRODUCT_ID) UUID productId, @Param(AttributeConstant.AGE) Integer age, @Param(AttributeConstant.GENDER) Integer gender);
}
