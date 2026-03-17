package com.insureflow.policy.entity;

import com.insureflow.policy.constant.TableConstant;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String policyNumber;

    private String customerName;

    private Integer age;

    private String agentId;

    private BigDecimal premium;

    private BigDecimal sumAssured;

    private String status;

    private LocalDateTime createdAt;
}
