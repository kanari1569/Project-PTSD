package com.ptsd.apigateway.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
@Slf4j
public class AimJwtFilter extends AbstractGatewayFilterFactory<AimJwtFilter.Config> {

    @Value("${AuthURL.url}")
    private String AUTH_SERVER_URL;

    public AimJwtFilter() {
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

            String Json;

            try {
                ResponseEntity<String> res = restTemplate.exchange(AUTH_SERVER_URL, HttpMethod.GET, req, String.class);
                Json = res.getBody();
            } catch (HttpClientErrorException e) {
                return onError(response, "Invaild Token", HttpStatus.FORBIDDEN);
            }

            HashMap<String, String > hashMap = extractFromJson(Json);

            exchange.getRequest().mutate().header("userId", hashMap.get("userId"));
            exchange.getRequest().mutate().header("subscribeState", hashMap.get("subscribeState"));

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

    private static HashMap<String, String> extractFromJson(String json) {
        try {
            // JSON 문자열 파싱
            JsonNode rootNode = new ObjectMapper().readTree(json);

            HashMap<String, String> hashMap = new HashMap<>();
            // "user_id" 값을 추출
            Long userId = rootNode.get("userId").asLong();

            String role = rootNode.get("subscribeState").toString();
            role = role.substring(1, role.length()-1);

            int subscribeState = -1;

            switch (role) {
                case "FREE" : subscribeState = 0;
                break;
                case "MINIMUM" : subscribeState = 1;
                break;
                case "STANDARD" : subscribeState = 2;
                break;
                case "PREMIUM" : subscribeState = 3;
                break;
                default:
                    break;
            }

            hashMap.put("userId", String.valueOf(userId));
            hashMap.put("subscribeState", String.valueOf(subscribeState));


            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static class Config {

    }


}
