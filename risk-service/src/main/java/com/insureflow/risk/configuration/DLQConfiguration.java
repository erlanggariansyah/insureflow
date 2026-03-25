package com.insureflow.risk.configuration;

import com.insureflow.risk.constant.TopicConstant;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.ConversionException;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.invocation.MethodArgumentResolutionException;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class DLQConfiguration {
    private static final long RETRY_INTERVAL = 1000L;
    private static final int MAX_RETRIES = 3;

    @Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(KafkaOperations<String, Object> kafkaOperations) {
        return new DeadLetterPublishingRecoverer(
                kafkaOperations,
                (record, exception) -> {
                    String dlqTopic = record.topic() + TopicConstant.DLQ_SUFFIX;
                    return new TopicPartition(dlqTopic, record.partition());
                }
        );
    }

    @Bean
    public DefaultErrorHandler sendToDLQ(DeadLetterPublishingRecoverer recoverer) {
        FixedBackOff backOff = new FixedBackOff(RETRY_INTERVAL, MAX_RETRIES);
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, backOff);

        handler.addNotRetryableExceptions(
                DeserializationException.class,
                MessageConversionException.class,
                MethodArgumentResolutionException.class,
                ConversionException.class,
                IllegalArgumentException.class
        );

        return handler;
    }
}
