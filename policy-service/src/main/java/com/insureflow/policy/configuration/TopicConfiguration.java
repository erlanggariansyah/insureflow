package com.insureflow.policy.configuration;

import com.insureflow.policy.constant.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {
    @Bean
    public NewTopic createPolicyTopic() {
        return new NewTopic(TopicConstant.POLICY_CREATED, 1, (short) 1);
    }
}
