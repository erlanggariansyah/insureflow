package com.insureflow.product.dto.request;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.exception.constraint.annotation.ExistRiderCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRiderItemRequest {
    @NotNull(message = MessageConstant.CODE_FIELD_MANDATORY)
    @NotBlank(message = MessageConstant.CODE_FIELD_NOT_BLANK)
    @ExistRiderCode
    private String code;

    @NotNull(message = MessageConstant.SUM_ASSURED_FIELD_MANDATORY)
    private BigDecimal sumAssured;
}
