package com.ptsd.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Value("${AuthURL.url}")
    private String AUTH_SERVER_URL;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Prefilter
        return ((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!containsAuthorization(request)) {
                return onError(response, "missing authorization header", HttpStatus.BAD_REQUEST);
            }

            String token = extractToken(request);
            token = token.replace("Bearer ", "");

            HttpHeaders headers =new HttpHeaders();
            headers.add("Authorization","Bearer "+token);

            HttpEntity<MultiValueMap<String, String>> req = new HttpEntity(headers);

            RestTemplate restTemplate = new RestTemplate();

//            ResponseEntity<String> res = restTemplate.exchange(AUTH_SERVER_URL, HttpMethod.GET, req, String.class);
//            log.info("res status code -> {}",res.getStatusCode());

            try {
                ResponseEntity<String> res = restTemplate.exchange(AUTH_SERVER_URL, HttpMethod.GET, req, String.class);
            } catch (HttpClientErrorException e) {
                return onError(response, "Invaild Token", HttpStatus.FORBIDDEN);
            }


            //PostFilter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
            }));

        });
    }

    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }


    private Mono<Void> onError(ServerHttpResponse response, String message, HttpStatus status) {
        response.setStatusCode(status);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    public static class Config {

    }

}
