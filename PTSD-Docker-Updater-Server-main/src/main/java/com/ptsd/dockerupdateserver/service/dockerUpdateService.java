package com.ptsd.dockerupdateserver.service;

import com.ptsd.dockerupdateserver.dto.responseDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface dockerUpdateService {
    public ResponseEntity<responseDto> rollingUpdate(String imageName) throws IOException, InterruptedException;

    public ResponseEntity<responseDto> scaleOut() throws IOException, InterruptedException;
}
