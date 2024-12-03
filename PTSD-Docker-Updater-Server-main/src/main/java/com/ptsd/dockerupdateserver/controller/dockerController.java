package com.ptsd.dockerupdateserver.controller;

import com.ptsd.dockerupdateserver.dto.responseDto;
import com.ptsd.dockerupdateserver.service.dockerUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dockers")
public class dockerController {

    @Autowired
    private final dockerUpdateService dockerUpdateService;

    @GetMapping("/{imagename}")
    public ResponseEntity<responseDto> rollingUpdate(@PathVariable(name = "imagename") String imageName) throws IOException, InterruptedException {
        return dockerUpdateService.rollingUpdate(imageName);
    }

    @GetMapping("/scale")
    public ResponseEntity<responseDto> scaleOut() throws IOException, InterruptedException {
        return dockerUpdateService.scaleOut();
    }

}
