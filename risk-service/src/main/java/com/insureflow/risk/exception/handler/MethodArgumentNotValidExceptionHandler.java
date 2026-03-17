package com.insureflow.risk.exception.handler;

import com.insureflow.risk.constant.MessageConstant;
import com.insureflow.risk.utils.APIResponse;
import com.insureflow.risk.utils.TraceId;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
