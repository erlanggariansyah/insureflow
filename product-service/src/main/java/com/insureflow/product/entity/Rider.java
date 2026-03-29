package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.RIDERS)
@Data
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.CODE, nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 100)
    private String name;

    @Column(name = ColumnConstant.RIDER_TYPE_ID)
    private UUID riderTypeId;

    @Column(name = ColumnConstant.METHODS)
    private Integer methodId;

    @Column(name = ColumnConstant.MIN_AGE)
    private Integer minAge;

    @Column(name = ColumnConstant.MAX_AGE)
    private Integer maxAge;

    @Column(name = ColumnConstant.MIN_SUM_ASSURED, precision = 15, scale = 2)
    private java.math.BigDecimal minSumAssured;

    @Column(name = ColumnConstant.MAX_SUM_ASSURED, precision = 15, scale = 2)
    private java.math.BigDecimal maxSumAssured;

    @Column(name = ColumnConstant.IS_ACTIVE)
    private Boolean isActive = true;

    @Column(name = ColumnConstant.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = ColumnConstant.CREATED_AT)
    private LocalDateTime createdAt;
}
