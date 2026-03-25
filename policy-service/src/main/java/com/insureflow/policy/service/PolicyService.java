package com.insureflow.policy.service;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.constant.PolicyConstant;
import com.insureflow.policy.dto.CreatePolicyResponse;
import com.insureflow.policy.dto.request.CreatePolicyRequest;
import com.insureflow.policy.entity.Policy;
import com.insureflow.policy.exception.throwable.KafkaPublishException;
import com.insureflow.policy.repository.PolicyRepository;
import com.insureflow.policy.stream.event.CreatePolicyEvent;
import com.insureflow.policy.stream.producer.PolicyProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final PolicyProducer policyProducer;

    @Transactional
    public CreatePolicyResponse post(CreatePolicyRequest request) throws KafkaPublishException {
        Policy policy = Policy.builder()
                .policyNumber(PolicyConstant.generatePolicyNumber())
                .customerName(request.getCustomerName())
                .age(request.getAge())
                .agentId(request.getAgentId())
                .premium(request.getPremium())
                .sumAssured(request.getSumAssured())
                .status(PolicyConstant.STATUS_SUBMITTED)
                .createdAt(LocalDateTime.now())
                .build();

        Policy savedPolicy = policyRepository.save(policy);

        CreatePolicyEvent event = CreatePolicyEvent.builder()
                .policyId(savedPolicy.getId())
                .customerName(savedPolicy.getCustomerName())
                .agentId(savedPolicy.getAgentId())
                .premium(savedPolicy.getPremium())
                .sumAssured(savedPolicy.getSumAssured())
                .build();

        policyProducer.createPolicyEvent(event);

        CreatePolicyResponse response = CreatePolicyResponse.builder()
                .id(savedPolicy.getId())
                .policyNumber(savedPolicy.getPolicyNumber())
                .customerName(savedPolicy.getCustomerName())
                .age(savedPolicy.getAge())
                .agentId(savedPolicy.getAgentId())
                .premium(savedPolicy.getPremium())
                .sumAssured(savedPolicy.getSumAssured())
                .status(savedPolicy.getStatus())
                .createdAt(savedPolicy.getCreatedAt())
                .build();

        return response;
    }

    @Cacheable(value = PolicyConstant.POLICY, key = "#id")
    public Policy getById(UUID id) {
        return policyRepository.findById(id).orElseThrow(() -> new RuntimeException(MessageConstant.POLICY_NOT_FOUND));
    }
}
