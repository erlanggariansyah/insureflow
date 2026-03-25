package com.insureflow.gateway.filters;

import com.insureflow.gateway.constants.AttributeConstant;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TraceFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = UUID.randomUUID().toString();
        ServerWebExchange mutated = exchange.mutate()
                .request(builder -> builder.header(AttributeConstant.X_TRACE_ID, traceId))
                .build();
        return chain.filter(mutated);
    }
}
