package com.insureflow.policy.stream.producer;

import com.insureflow.policy.constant.TopicConstant;
import com.insureflow.policy.stream.event.CreatePolicyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createPolicyEvent(CreatePolicyEvent event) {
        kafkaTemplate.send(
                TopicConstant.POLICY_CREATED,
                event.getPolicyId().toString(),
                event
        );
    }
}
