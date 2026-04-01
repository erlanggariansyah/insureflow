package com.openlife.product.exception.handler;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.util.APIResponse;
import com.openlife.product.util.TraceId;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<Object> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(MessageConstant.INVALID_REQUEST);

        return APIResponse.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .traceId(TraceId.getTraceId())
                .data(null)
                .build();
    }
}
