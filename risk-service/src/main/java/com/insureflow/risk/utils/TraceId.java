package com.insureflow.risk.utils;

import org.slf4j.MDC;

import java.util.UUID;

import static com.insureflow.risk.filter.TraceId.TRACE_ID;

public class TraceId {
    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}
