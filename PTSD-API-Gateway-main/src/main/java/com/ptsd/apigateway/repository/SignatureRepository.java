package com.ptsd.apigateway.repository;

import com.ptsd.apigateway.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, String> {
}
