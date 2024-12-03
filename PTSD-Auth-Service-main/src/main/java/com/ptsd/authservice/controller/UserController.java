package com.ptsd.authservice.controller;

import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.dto.AimResPonse;
import com.ptsd.authservice.dto.UserDto;
import com.ptsd.authservice.dto.UserResponse;
import com.ptsd.authservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/{userid}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable(name = "userid") Long userId) {
        return ResponseEntity.ok(userServiceImpl.getUserInfo(userId));
    }

    @PutMapping("/{userid}/nickname")
    public ResponseEntity<UserResponse> userInfoDetail(@PathVariable(name = "userid") Long userId, @RequestBody HashMap<String, Object> params) {
        return ResponseEntity.ok().body(userServiceImpl.editNickname(userId, (String) params.get("nickname")));
    }

    @PutMapping("/{userid}/upload")
    public ResponseEntity<UserResponse> uploadFile(@PathVariable(name = "userid") Long userId, @RequestPart(value = "file") MultipartFile multipartFile) {
        return ResponseEntity.ok().body(userServiceImpl.editProfileImage(userId, multipartFile));
    }

    @PutMapping("/{userid}/subscribestate")
    public ResponseEntity<AimResPonse> editSubscribeState(@PathVariable(name = "userid") Long userId, @RequestBody HashMap<String, Object> params) {
        return ResponseEntity.ok().body(userServiceImpl.editSubscribeState(userId, (Integer) params.get("subscribeState"), (String) params.get("instanceId")));
    }


}
