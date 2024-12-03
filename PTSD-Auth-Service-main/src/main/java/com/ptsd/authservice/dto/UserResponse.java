package com.ptsd.authservice.dto;

import com.ptsd.authservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.fasterxml.jackson.databind.ObjectMapper;

@AllArgsConstructor
@Builder
@Getter
public class UserResponse {

    private Long userId;
    private String socialId;
    private String status;
    private String token;
    private String nickname;
    private String email;
    private String platform;
    private String profile_image;
    private Role subscribeState;
    private String instanceId;

    public String toJson(){
        String userResponseJson;
        try {
            userResponseJson = new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            // TODO: handle exception
            userResponseJson = "";
        }
        return userResponseJson;
    }
}