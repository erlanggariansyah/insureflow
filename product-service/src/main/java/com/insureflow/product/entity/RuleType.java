package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = TableConstant.RULE_TYPES)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RuleType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.NAME, nullable = false, unique = true)
    private String name;
}
