package com.ptsd.authservice.service;

import com.ptsd.authservice.domain.Role;
import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.dto.AimResPonse;
import com.ptsd.authservice.dto.UserDto;
import com.ptsd.authservice.dto.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService{

    public Long join(UserDto userDto);
    public UserResponse getUserInfo(Long id);
    public UserResponse editNickname(Long id, String nickname);
    public UserResponse editProfileImage(Long id, MultipartFile profileImage);
    public AimResPonse editSubscribeState(Long id, Integer integer, String instanceId);
    public Long searchUserId(String socialId, String platform);
}
