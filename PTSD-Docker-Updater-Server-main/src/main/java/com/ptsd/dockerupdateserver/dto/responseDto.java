package com.ptsd.dockerupdateserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class responseDto {

    private String status;
    private String message;

}
