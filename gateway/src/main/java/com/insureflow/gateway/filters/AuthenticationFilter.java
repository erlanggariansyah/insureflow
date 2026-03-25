package com.insureflow.gateway.filters;

import com.insureflow.gateway.constants.AttributeConstant;
import com.insureflow.gateway.constants.PathConstant;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter {
    private final List<String> openApi = List.of(PathConstant.AUTH_V1);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (openApi.stream().anyMatch(path::startsWith))
            return chain.filter(exchange);

        // Sementara Auth Service belum dibuat
        String authHeader = exchange.getRequest().getHeaders().getFirst(AttributeConstant.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(AttributeConstant.BEARER)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
