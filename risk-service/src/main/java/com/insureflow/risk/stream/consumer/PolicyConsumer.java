package com.insureflow.risk.stream.consumer;

import com.insureflow.risk.constant.TopicConstant;
import com.insureflow.risk.stream.event.CreatePolicyEvent;
import com.insureflow.risk.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyConsumer {
    private final RiskService riskService;

    @KafkaListener(topics = TopicConstant.POLICY_CREATED, groupId = "risk-service", errorHandler = "sendToDLQ")
    public void consume(CreatePolicyEvent event) {
        riskService.assessRisk(event);
    }
}
