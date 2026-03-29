package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = TableConstant.PRODUCT_TYPES)
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.NAME, nullable = false, unique = true, length = 30)
    private String name;
}
