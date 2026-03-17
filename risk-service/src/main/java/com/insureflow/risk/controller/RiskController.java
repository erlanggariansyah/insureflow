package com.insureflow.risk.controller;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.constant.PathConstant;
import com.insureflow.risk.constant.URIConstant;
import com.insureflow.risk.dto.response.RiskResponse;
import com.insureflow.risk.service.RiskService;
import com.insureflow.risk.utils.APIResponse;
import com.insureflow.risk.utils.TraceId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(PathConstant.RISKS)
@RequiredArgsConstructor
public class RiskController {
    private final RiskService riskService;

    @GetMapping(URIConstant.BY_POLICY_ID)
    public APIResponse<RiskResponse> get(@PathVariable UUID policyId) {
        RiskResponse response = riskService.getByPolicyId(policyId);
        return APIResponse.<RiskResponse>builder()
                .message(MessageConstant.RISK_RETRIEVE_SUCCESS)
                .status(200)
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }
}
