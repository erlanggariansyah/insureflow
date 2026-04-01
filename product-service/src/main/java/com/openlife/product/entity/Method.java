package com.openlife.product.entity;

import com.openlife.product.constant.ColumnConstant;
import com.openlife.product.constant.TableConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = TableConstant.METHODS)
@Data
public class Method {
    @Id
    @Column(name = ColumnConstant.ID)
    private Integer id;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 50)
    private String name;
}
