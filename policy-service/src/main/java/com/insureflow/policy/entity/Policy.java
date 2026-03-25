package com.insureflow.policy.entity;

import com.insureflow.policy.constant.TableConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.POLICIES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(nullable = false)
    @Min(18)
    @Max(100)
    private Integer age;

    @Column(nullable = false)
    private String agentId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal premium;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal sumAssured;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
