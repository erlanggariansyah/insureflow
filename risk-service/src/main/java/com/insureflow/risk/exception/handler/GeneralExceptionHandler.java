package com.insureflow.risk.exception.handler;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.utils.APIResponse;
import com.insureflow.risk.utils.TraceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
