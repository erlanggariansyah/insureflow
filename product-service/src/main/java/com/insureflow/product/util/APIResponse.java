package com.insureflow.product.util;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.insureflow.product.constant.AttributeConstant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({AttributeConstant.STATUS, AttributeConstant.MESSAGE, AttributeConstant.TRACE_ID, AttributeConstant.DATA})
public class APIResponse<T> {
    private int status;
    private String message;
    private String traceId;
    private T data;
}
