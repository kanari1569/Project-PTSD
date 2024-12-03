package com.ptsd.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomAuthFilter extends AbstractGatewayFilterFactory<CustomAuthFilter.Config> {
    public CustomAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom PreFilter
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!request.getHeaders().containsKey("token")) {
                return handleUnAuthorized(exchange);
            }

            //Custom PostFilter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom Post filter: response code -> {}", response.getStatusCode());
            }));
        });
    }

    private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        log.info(response.getStatusCode().toString());
        return response.setComplete();
    }

    public static class Config {

    }
}