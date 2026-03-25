package com.insureflow.policy.exception.throwable;

import lombok.Getter;

@Getter
public class KafkaPublishException extends RuntimeException {
    private final String topic;
    private final String eventKey;
    private final String eventType;

    public KafkaPublishException(String message, Throwable cause, String topic, String eventKey, String eventType) {
        super(message, cause);
        this.topic = topic;
        this.eventKey = eventKey;
        this.eventType = eventType;
    }
}
