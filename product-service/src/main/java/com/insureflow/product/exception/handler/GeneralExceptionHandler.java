package com.insureflow.product.exception.handler;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.util.APIResponse;
import com.insureflow.product.util.TraceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<Object> handle(Exception ex) {
        log.error(MessageConstant.ERROR_DETAILS_PLACEHOLDER, ex);

        return APIResponse.builder()
                .message(MessageConstant.INTERNAL_SERVER_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
