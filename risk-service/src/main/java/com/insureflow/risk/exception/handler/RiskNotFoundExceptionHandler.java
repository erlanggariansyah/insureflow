package com.insureflow.risk.exception.handler;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.exception.throwable.RiskNotFoundException;
import com.insureflow.risk.utils.APIResponse;
import com.insureflow.risk.utils.TraceId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RiskNotFoundExceptionHandler {
    @ExceptionHandler(RiskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse<Object> handleRiskNotFound(RiskNotFoundException ex) {
        return APIResponse.builder()
                .message(MessageConstant.RISK_NOT_FOUND)
                .status(HttpStatus.NOT_FOUND.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
