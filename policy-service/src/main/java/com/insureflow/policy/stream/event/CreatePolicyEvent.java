package com.insureflow.policy.stream.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CreatePolicyEvent {
    private UUID policyId;
    private String customerName;
    private String agentId;
    private BigDecimal premium;
    private BigDecimal sumAssured;
}
