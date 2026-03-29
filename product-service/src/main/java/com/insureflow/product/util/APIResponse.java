package com.insureflow.product.util;

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
