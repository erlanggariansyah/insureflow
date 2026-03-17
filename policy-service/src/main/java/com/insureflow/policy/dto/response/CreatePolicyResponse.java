package com.insureflow.policy.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreatePolicyResponse {
    private UUID id;
    private String policyNumber;
    private String customerName;
    private Integer age;
    private String agentId;
    private BigDecimal premium;
    private BigDecimal sumAssured;
    private String status;
    private LocalDateTime createdAt;
}
