package com.insureflow.policy.dto.request;

import com.insureflow.policy.constant.MessageConstant;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePolicyRequest {
    @NotBlank(message = MessageConstant.CUSTOMER_NAME_MANDATORY)
    private String customerName;

    @NotNull(message = MessageConstant.AGE_MANDATORY)
    @Min(value = 18, message = MessageConstant.AGE_MIN)
    @Max(value = 100, message = MessageConstant.AGE_MAX)
    private Integer age;

    @NotBlank(message = MessageConstant.AGENT_ID_MANDATORY)
    private String agentId;

    @NotNull(message = MessageConstant.PREMIUM_MANDATORY)
    @Positive(message = MessageConstant.PREMIUM_POSITIVE)
    private BigDecimal premium;

    @NotNull(message = MessageConstant.SUM_ASSURED_MANDATORY)
    @Positive(message = MessageConstant.SUM_ASSURED_POSITIVE)
    private BigDecimal sumAssured;
}
