package com.ptsd.apigateway.service;

import com.ptsd.apigateway.dto.SignatureDto;

public interface SignatureService {

    public String create(String code, String service);
}
