package com.insureflow.risk.service;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.constant.RiskConstant;
import com.insureflow.risk.exception.throwable.RiskNotFoundException;
import com.insureflow.risk.stream.event.CreatePolicyEvent;
import com.insureflow.risk.dto.response.RiskResponse;
import com.insureflow.risk.entity.Risk;
import com.insureflow.risk.repository.RiskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class RiskService {
    private final RiskRepository riskRepository;

    @Transactional
    public void assessRisk(CreatePolicyEvent event) {
        Optional<Risk> existingRisk = riskRepository.findByPolicyId(event.getPolicyId());
        if (existingRisk.isPresent()) {
            log.info(MessageConstant.RISK_ALREADY_ASSESSED, event.getPolicyId());
            return;
        }

        double ratio = event
                .getSumAssured()
                .divide(event.getPremium(), 2)
                .doubleValue();

        String riskLevel;
        double riskScore;

        if (ratio > RiskConstant.highThreshold) {
            riskLevel = RiskConstant.LEVEL_HIGH;
            riskScore = RiskConstant.highScore;
        } else if (ratio > RiskConstant.mediumThreshold) {
            riskLevel = RiskConstant.LEVEL_MEDIUM;
            riskScore = RiskConstant.mediumScore;
        } else {
            riskLevel = RiskConstant.LEVEL_LOW;
            riskScore = RiskConstant.lowScore;
        }

        Risk risk = Risk.builder()
                .policyId(event.getPolicyId())
                .riskLevel(riskLevel)
                .riskScore(riskScore)
                .assessedAt(LocalDateTime.now())
                .build();

        riskRepository.save(risk);
    }

    public RiskResponse getByPolicyId(java.util.UUID policyId) throws RiskNotFoundException {
        Risk risk = riskRepository.findByPolicyId(policyId).orElseThrow(() -> new RiskNotFoundException(MessageConstant.RISK_NOT_FOUND));
        return RiskResponse.builder()
                .policyId(risk.getPolicyId())
                .riskLevel(risk.getRiskLevel())
                .riskScore(risk.getRiskScore())
                .assessedAt(risk.getAssessedAt())
                .build();
    }
}
