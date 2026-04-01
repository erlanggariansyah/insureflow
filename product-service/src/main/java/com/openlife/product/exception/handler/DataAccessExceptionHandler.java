package com.openlife.product.exception.handler;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.util.APIResponse;
import com.openlife.product.util.TraceId;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
