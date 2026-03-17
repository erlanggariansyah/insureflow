package com.insureflow.policy.exception.handler;

import com.insureflow.policy.constant.MessageConstant;
import com.insureflow.policy.utils.APIResponse;
import com.insureflow.policy.utils.TraceId;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class DataAccessExceptionHandler {
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<Object> handleDatabase(DataAccessException ex) {
        return APIResponse.builder()
                .message(MessageConstant.DATABASE_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
