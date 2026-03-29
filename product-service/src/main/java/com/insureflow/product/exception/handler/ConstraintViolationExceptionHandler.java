package com.insureflow.product.exception.handler;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.dto.ValidationError;
import com.insureflow.product.util.APIResponse;
import com.insureflow.product.util.TraceId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ConstraintViolationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<List<ValidationError>>> handleConstraintViolation(ConstraintViolationException ex) {
        List<ValidationError> errors = ex.getConstraintViolations().stream()
                .map(violation -> new ValidationError(
                        getFieldName(violation),
                        violation.getMessage()
                ))
                .collect(Collectors.toList());

        APIResponse<List<ValidationError>> response = APIResponse.<List<ValidationError>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(MessageConstant.BAD_REQUEST)
                .traceId(TraceId.getTraceId())
                .data(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    private String getFieldName(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }
}
