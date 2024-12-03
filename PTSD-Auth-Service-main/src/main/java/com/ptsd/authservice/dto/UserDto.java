package com.ptsd.authservice.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptsd.authservice.domain.Role;
import com.ptsd.authservice.domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {
    private String socialId;
    private String email;
    private String nickname;
    private String platform;
    private String profileImage;
    private Role subscribe_state;

    public User toEntity() {
        User user = User.builder()
                .social_id(socialId)
                .email(email)
                .nickname(nickname)
                .platform(platform)
                .profileImage(profileImage)
                .subscribe_state(subscribe_state)
                .build();
        return user;
    }

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