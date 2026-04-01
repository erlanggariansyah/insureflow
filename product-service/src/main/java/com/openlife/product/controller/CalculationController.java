package com.openlife.product.controller;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.constant.PathConstant;
import com.openlife.product.dto.request.CalculateRequest;
import com.openlife.product.dto.response.CalculateResponse;
import com.openlife.product.service.CalculationService;
import com.openlife.product.util.APIResponse;
import com.openlife.product.util.TraceId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstant.CALCULATIONS_v1)
@RequiredArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping
    public APIResponse<CalculateResponse> calculate(@Valid @ModelAttribute CalculateRequest request) {
        CalculateResponse response = calculationService.calculate(request);
        return APIResponse.<CalculateResponse>builder()
                .message(MessageConstant.CALCULATION_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }
}
