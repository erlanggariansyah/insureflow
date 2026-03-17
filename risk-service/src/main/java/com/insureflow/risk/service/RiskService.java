package com.insureflow.risk.service;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.constant.RiskConstant;
import com.insureflow.risk.stream.event.CreatePolicyEvent;
import com.insureflow.risk.dto.response.RiskResponse;
import com.insureflow.risk.entity.Risk;
import com.insureflow.risk.repository.RiskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RiskService {
    private final RiskRepository riskRepository;

    public void assessRisk(CreatePolicyEvent event) {
        double ratio = event
                .getSumAssured()
                .divide(event.getPremium(), 2)
                .doubleValue();

        String riskLevel;
        double riskScore;

        if (ratio > 500) {
            riskLevel = RiskConstant.LEVEL_HIGH;
            riskScore = 0.9;
        } else if (ratio > 200) {
            riskLevel = RiskConstant.LEVEL_MEDIUM;
            riskScore = 0.6;
        } else {
            riskLevel = RiskConstant.LEVEL_LOW;
            riskScore = 0.3;
        }

        Risk risk = Risk.builder()
                .policyId(event.getPolicyId())
                .riskLevel(riskLevel)
                .riskScore(riskScore)
                .assessedAt(LocalDateTime.now())
                .build();

        riskRepository.save(risk);
    }

    public RiskResponse getByPolicyId(java.util.UUID policyId) {
        Risk risk = riskRepository.findByPolicyId(policyId).orElseThrow(() -> new RuntimeException(MessageConstant.RISK_NOT_FOUND));
        return RiskResponse.builder()
                .policyId(risk.getPolicyId())
                .riskLevel(risk.getRiskLevel())
                .riskScore(risk.getRiskScore())
                .assessedAt(risk.getAssessedAt())
                .build();
    }
}
