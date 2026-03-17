package com.insureflow.policy.controller;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.constant.PathConstant;
import com.insureflow.policy.constant.URIConstant;
import com.insureflow.policy.dto.CreatePolicyResponse;
import com.insureflow.policy.dto.request.CreatePolicyRequest;
import com.insureflow.policy.entity.Policy;
import com.insureflow.policy.service.PolicyService;
import com.insureflow.policy.utils.APIResponse;
import com.insureflow.policy.utils.TraceId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(PathConstant.POLICIES_V1)
@RequiredArgsConstructor
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping
    public APIResponse<CreatePolicyResponse> post(@Valid @RequestBody CreatePolicyRequest request) {
        CreatePolicyResponse response = policyService.post(request);
        return APIResponse.<CreatePolicyResponse>builder()
                .message(MessageConstant.POLICY_CREATION_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.BY_ID)
    public APIResponse<Policy> getPolicy(@PathVariable UUID id) {
        Policy response = policyService.getById(id);
        return APIResponse.<Policy>builder()
                .message(MessageConstant.POLICY_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }
}
