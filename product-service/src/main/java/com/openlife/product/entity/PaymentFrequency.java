package com.openlife.product.entity;

import com.openlife.product.constant.ColumnConstant;
import com.openlife.product.constant.TableConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = TableConstant.PAYMENT_FREQUENCIES)
@Data
public class PaymentFrequency {
    @Id
    @Column(name = ColumnConstant.ID)
    private Integer id;

    @Column(name = ColumnConstant.CODE, nullable = false, length = 50)
    private String code;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 50)
    private String name;

    @Column(name = ColumnConstant.FACTOR, nullable = false)
    private java.math.BigDecimal factor;

    @Column(name = ColumnConstant.IS_ACTIVE)
    private Boolean isActive = true;
}
