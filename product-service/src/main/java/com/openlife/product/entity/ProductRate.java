package com.openlife.product.entity;

import com.openlife.product.constant.ColumnConstant;
import com.openlife.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.PRODUCT_RATES)
@Data
public class ProductRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.PRODUCT_ID)
    private UUID productId;

    @Column(name = ColumnConstant.AGE)
    private Integer age;

    @Column(name = ColumnConstant.GENDER)
    private Integer gender;

    @Column(name = ColumnConstant.RATE, nullable = false, precision = 10, scale = 6)
    private java.math.BigDecimal rate;

    @Column(name = ColumnConstant.IS_ACTIVE)
    private Boolean isActive = true;

    @Column(name = ColumnConstant.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = ColumnConstant.CREATED_AT)
    private LocalDateTime createdAt;
}
