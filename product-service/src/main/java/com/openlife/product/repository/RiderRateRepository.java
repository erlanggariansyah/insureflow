package com.openlife.product.repository;

import com.openlife.product.constant.AttributeConstant;
import com.openlife.product.entity.RiderRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface RiderRateRepository extends JpaRepository<RiderRate, UUID> {
    @Query(value = """
    SELECT rr.rate FROM rider_rates rr
    WHERE rr.rider_id = :riderId
      AND rr.is_active = true
      AND (rr.gender = :gender OR rr.gender IS NULL)
      AND rr.age <= :age
    ORDER BY
        rr.age DESC,
        rr.gender DESC NULLS LAST,
        rr.updated_at DESC
    LIMIT 1
    """, nativeQuery = true)
    BigDecimal findRate(@Param(AttributeConstant.RIDER_ID) UUID riderId, @Param(AttributeConstant.AGE) Integer age, @Param(AttributeConstant.GENDER) Integer gender);
}
