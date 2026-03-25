package com.insureflow.policy.stream.producer;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.constant.TopicConstant;
import com.insureflow.policy.exception.throwable.KafkaPublishException;
import com.insureflow.policy.stream.event.CreatePolicyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class PolicyProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createPolicyEvent(CreatePolicyEvent event) throws KafkaPublishException {
        try {
            SendResult<String, Object> result = kafkaTemplate.send(
                    TopicConstant.POLICY_CREATED,
                    event.getPolicyId().toString(),
                    event
            ).get(5, TimeUnit.SECONDS);

            log.info(
                    MessageConstant.EVENT_PUBLISH_SUCCESS,
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset()
            );
        } catch (Exception e) {
            throw new KafkaPublishException(
                    MessageConstant.EVENT_PUBLISH_FAILED,
                    e.getCause(),
                    TopicConstant.POLICY_CREATED,
                    event.getPolicyId().toString(),
                    this.getClass().getSimpleName()
            );
        }
    }
}
