//package com.ptsd.apigateway.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@RequiredArgsConstructor
//@Slf4j
//public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
//    private final ObjectMapper objectMapper;
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        ServerHttpResponse response = exchange.getResponse();
//
//        if (response.isCommitted()) {
//            return Mono.error(ex);
//        }
//
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//        if (ex instanceof ResponseStatusException) {
//            response.setStatusCode(((ResponseStatusException) ex).getStatusCode());
//        }
//
//        Map<String, String> errorMap = new HashMap<>();
//        String statusCode = Objects.requireNonNull(response.getStatusCode()).toString();
//        if(statusCode.split(" ").length == 2) {
//            errorMap.put("ErrorCode", response.getStatusCode().toString().split(" ")[0]);
//            errorMap.put("ErrorMsg", response.getStatusCode().toString().split(" ")[1]);
//        }
//
//        String error = "Gateway Error";
//        try {
//            error = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
//        } catch (JsonProcessingException e) {
//            log.error("JsonProcessingException : " + e.getMessage());
//        }
//
//        byte[] bytes = error.getBytes(StandardCharsets.UTF_8);
//        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//        return exchange.getResponse().writeWith(Flux.just(buffer));
//
//
//    }
//}
