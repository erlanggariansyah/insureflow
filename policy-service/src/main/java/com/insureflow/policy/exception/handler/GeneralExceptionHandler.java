package com.insureflow.policy.exception.handler;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.utils.APIResponse;
import com.insureflow.policy.utils.TraceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<Object> handle(Exception ex) {
        log.error("Error details: ", ex);

        return APIResponse.builder()
                .message(MessageConstant.INTERNAL_SERVER_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
