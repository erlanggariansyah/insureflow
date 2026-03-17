package com.insureflow.risk.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class RiskResponse {
    private UUID policyId;
    private String riskLevel;
    private Double riskScore;
    private LocalDateTime assessedAt;
}
