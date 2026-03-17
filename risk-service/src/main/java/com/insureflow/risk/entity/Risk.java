package com.insureflow.risk.entity;

import com.insureflow.risk.constant.TableConstant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = TableConstant.RISKS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Risk {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID policyId;
    private String riskLevel;
    private Double riskScore;
    private LocalDateTime assessedAt;
}
