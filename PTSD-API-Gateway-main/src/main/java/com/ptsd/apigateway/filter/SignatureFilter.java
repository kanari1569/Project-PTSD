package com.ptsd.apigateway.filter;

import com.ptsd.apigateway.repository.SignatureRepository;
import lombok.extern.slf4j.Slf4j;
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

@Component
@Slf4j
public class SignatureFilter extends AbstractGatewayFilterFactory<SignatureFilter.Config> {

    private final SignatureRepository signatureRepository;

    public SignatureFilter(SignatureRepository signatureRepository) {
        super(Config.class);
        this.signatureRepository = signatureRepository;
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Prefilter
        return ((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String sig = request.getHeaders().get("SIG").get(0);

            log.info("sig = "+sig);

            if (!signatureRepository.existsById(sig)) {
                return onError(response, "Invaild Signature", HttpStatus.FORBIDDEN);
            }

            //PostFilter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
            }));

        });
    }


    public static class Config {

    }

    private Mono<Void> onError(ServerHttpResponse response, String message, HttpStatus status) {
        response.setStatusCode(status);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
