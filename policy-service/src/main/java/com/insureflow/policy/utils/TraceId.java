package com.insureflow.policy.utils;

import java.util.UUID;
import org.slf4j.MDC;

import static com.insureflow.policy.filter.TraceId.TRACE_ID;

public class TraceId {
    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}
