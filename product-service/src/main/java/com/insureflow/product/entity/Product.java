package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.PRODUCTS)
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.CODE, nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 100)
    private String name;

    @Column(name = ColumnConstant.PRODUCT_TYPE_ID)
    private UUID productTypeId;

    @Column(name = ColumnConstant.IS_ACTIVE)
    private Boolean isActive = true;

    @Column(name = ColumnConstant.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = ColumnConstant.CREATED_AT)
    private LocalDateTime createdAt;
}
