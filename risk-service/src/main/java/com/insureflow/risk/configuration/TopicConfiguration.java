package com.insureflow.risk.configuration;

import com.insureflow.risk.constant.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class TopicConfiguration {
    @Bean
    public NewTopic createPolicyDLQTopic() {
        return new NewTopic(TopicConstant.POLICY_CREATED + TopicConstant.DLQ_SUFFIX, 1, (short) 1);
    }
}
