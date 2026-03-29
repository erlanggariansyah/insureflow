package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.PRODUCT_RIDERS)
@Data
public class ProductRider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.PRODUCT_ID)
    private UUID productId;

    @Column(name = ColumnConstant.RIDER_ID)
    private UUID riderId;

    @Column(name = ColumnConstant.IS_MANDATORY)
    private Boolean isMandatory = false;

    @Column(name = ColumnConstant.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = ColumnConstant.CREATED_AT)
    private LocalDateTime createdAt;
}
