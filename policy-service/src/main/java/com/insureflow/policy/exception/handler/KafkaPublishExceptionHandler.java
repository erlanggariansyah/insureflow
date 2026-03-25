package com.insureflow.policy.exception.handler;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.exception.throwable.KafkaPublishException;
import com.insureflow.policy.utils.APIResponse;
import com.insureflow.policy.utils.TraceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class KafkaPublishExceptionHandler {
    @ExceptionHandler(KafkaPublishException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<Object> handleKafka(KafkaPublishException ex) {
        log.error(MessageConstant.ERROR_DETAILS_PLACEHOLDER, ex);

        return APIResponse.builder()
                .message(MessageConstant.EVENT_PUBLISH_FAILED)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
