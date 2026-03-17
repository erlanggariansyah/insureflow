package com.insureflow.risk.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {
    private String message;
    private int status;
    private String traceId;
    private T data;
}
