package com.ptsd.apigateway.dto;

import com.ptsd.apigateway.entity.Signature;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class SignatureDto {
    private String code;
    private String service;

    public Signature toEntity() {
        Signature signature = Signature.builder()
                .code(code)
                .service(service)
                .build();
        return signature;
    }


}
