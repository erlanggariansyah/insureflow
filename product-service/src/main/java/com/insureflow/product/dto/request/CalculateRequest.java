package com.insureflow.product.dto.request;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.exception.constraint.annotation.ExistGender;
import com.insureflow.product.exception.constraint.annotation.ExistPaymentFrequencyId;
import com.insureflow.product.exception.constraint.annotation.ExistProductCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRequest {
    @NotNull(message = MessageConstant.CODE_FIELD_MANDATORY)
    @NotBlank(message = MessageConstant.CODE_FIELD_NOT_BLANK)
    @ExistProductCode
    private String code;

    @NotNull(message = MessageConstant.AGE_FIELD_MANDATORY)
    private Integer age;

    @ExistGender
    private Integer gender;

    @NotNull(message = MessageConstant.SUM_ASSURED_FIELD_MANDATORY)
    private BigDecimal sumAssured;

    @NotNull(message = MessageConstant.PAYMENT_FREQUENCY_ID_FIELD_MANDATORY)
    @ExistPaymentFrequencyId
    private Integer paymentFrequencyId;

    private List<CalculateRiderItemRequest> riders;
}
