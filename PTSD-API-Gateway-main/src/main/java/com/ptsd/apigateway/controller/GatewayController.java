package com.ptsd.apigateway.controller;


import com.ptsd.apigateway.dto.ResponseDto;
import com.ptsd.apigateway.entity.Signature;
import com.ptsd.apigateway.service.SignatureServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gateways")
public class GatewayController {

    @Autowired
    SignatureServiceImpl signatureServiceImpl;

    @GetMapping("/healthcheck")
    public ResponseEntity<ResponseDto> healthCheck() {

        ResponseDto responseDto = ResponseDto.builder()
                .status("healthy")
                .message("Sever ON")
                .build();
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping("/signature")
    public String CreateSignature(@RequestBody Signature signature) {

        String code = signature.getCode();
        String service = signature.getService();


        return signatureServiceImpl.create(code, service);
    }

}
