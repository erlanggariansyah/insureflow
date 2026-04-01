package com.insureflow.product.entity;

import com.insureflow.product.constant.ColumnConstant;
import com.insureflow.product.constant.TableConstant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.RULES)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ColumnConstant.ID)
    private UUID id;

    @Column(name = ColumnConstant.SUB_ID, nullable = false)
    private UUID subId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ColumnConstant.TYPE_ID)
    private RuleType type;

    @Column(name = ColumnConstant.NAME, nullable = false)
    private String name;

    @Column(name = ColumnConstant.EXPRESSION, nullable = false, columnDefinition = "TEXT")
    private String expression;

    @Column(name = ColumnConstant.MESSAGE)
    private String message;

    @CreationTimestamp
    @Column(name = ColumnConstant.CREATED_AT, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = ColumnConstant.UPDATED_AT)
    private LocalDateTime updatedAt;
}
