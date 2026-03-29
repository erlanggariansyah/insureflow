package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = TableConstant.GENDERS)
@Data
public class Gender {
    @Id
    @Column(name = ColumnConstant.ID)
    private Integer id;

    @Column(name = ColumnConstant.NAME, nullable = false, length = 10)
    private String name;
}
