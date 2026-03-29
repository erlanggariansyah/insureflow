package com.insureflow.product.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CalculateResponse {
    private String productCode;
    private BigDecimal basePremium;
    private List<CalculateRiderItemResponse> riders;
    private BigDecimal totalPremium;
}
