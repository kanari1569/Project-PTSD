package com.ptsd.apigateway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "signature")
@Entity
public class Signature {

    @Id @Column(name = "code")
    String code;

    @Column(name = "service", nullable = false)
    String service;

    @Builder
    public Signature(String code, String service) {
        this.code = code;
        this.service = service;
    }

}
