package com.ptsd.authservice.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptsd.authservice.domain.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AimResPonse {

    private String status;
    private String token;
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
