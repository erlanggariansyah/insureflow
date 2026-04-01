package com.openlife.product.entity;

import com.openlife.product.constant.ColumnConstant;
import com.openlife.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = TableConstant.RIDER_TYPES)
@Data
public class RiderType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.CODE, nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 50)
    private String name;
}
