package com.insureflow.product.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalculateRiderItemResponse {
    private String code;
    private BigDecimal premium;
}
