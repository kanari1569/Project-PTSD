package com.ptsd.authservice.service;


import com.ptsd.authservice.SocialLogin.LoginTokenManager;
import com.ptsd.authservice.domain.Role;
import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.dto.AimResPonse;
import com.ptsd.authservice.dto.UserDto;
import com.ptsd.authservice.dto.UserResponse;
import com.ptsd.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final LoginTokenManager loginTokenManager;

    @Transactional
    public Long join(UserDto dto) {
        return userRepository.save(dto.toEntity()).getUserId();
    }

    public UserResponse getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("존재하지 않는 회원입니다.")));

        String token = loginTokenManager.genToken(user);

        UserResponse userResponse = UserResponse.builder()
                .userId(user.getUserId())
                .socialId(user.getSocialId())
                .email(user.getEmail())
                .status("Success")
                .nickname(user.getNickname())
                .subscribeState(user.getSubscribe_state())
                .token(token)
                .profile_image(user.getProfileImage())
                .build();

        return userResponse;
    }

    @Transactional
    public UserResponse editNickname(Long id, String nickname) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("존재하지 않는 회원입니다.")));
        user.update(nickname);

        String token = loginTokenManager.genToken(user);

        UserResponse userResponse = UserResponse.builder()
                .status("Success")
                .nickname(user.getNickname())
                .token(token)
                .build();

        return userResponse;
    }
    @Transactional
    public AimResPonse editSubscribeState(Long id, Integer integer, String instanceId) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("존재하지 않는 회원입니다.")));

        Role role = null;

        switch (integer) {
            case 0 -> role = Role.FREE;
            case 1 -> role = Role.MINIMUM;
            case 2 -> role = Role.STANDARD;
            case 3 -> role = Role.PREMIUM;
            default -> {
            }
        }

        user.updateSubScribeState(role);
        user.updateInstanceId(instanceId);

        String token = loginTokenManager.genToken(user);

        return AimResPonse.builder()
                .status("Success")
                .subscribeState(user.getSubscribe_state())
                .instanceId(user.getInstance_id())
                .token(token)
                .build();
    }



    @Transactional
    public UserResponse editProfileImage(Long id, MultipartFile profileImage) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("존재하지 않는 회원입니다.")));

        String profileURL = awsS3Uploader.uploadImage(profileImage);
        user.updateProfileImage(profileURL);

        String token = loginTokenManager.genToken(user);

        return UserResponse.builder()
                .status("Success")
                .token(token)
                .profile_image(user.getProfileImage())
                .build();

    }


    public Long searchUserId(String socialId, String platform) {
        Optional<User> user = userRepository.findBySocialIdAndPlatform(socialId, platform);
        return user.get().getUserId();
    }


}