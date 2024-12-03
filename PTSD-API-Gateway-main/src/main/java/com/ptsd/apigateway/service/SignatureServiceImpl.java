package com.ptsd.apigateway.service;

import com.ptsd.apigateway.dto.SignatureDto;
import com.ptsd.apigateway.repository.SignatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignatureServiceImpl implements SignatureService{

    private final SignatureRepository signatureRepository;

    @Transactional
    public String create(String code, String service) {

        SignatureDto dto = SignatureDto.builder()
                .code(code)
                .service(service)
                .build();

        return signatureRepository.save(dto.toEntity()).getCode();
    }

}
