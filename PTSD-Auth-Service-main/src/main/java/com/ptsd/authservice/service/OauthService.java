package com.ptsd.authservice.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

import com.ptsd.authservice.SocialLogin.Kakao.KakaoOauth;
import com.ptsd.authservice.SocialLogin.Kakao.KakaoOauthToken;
import com.ptsd.authservice.SocialLogin.Kakao.KakaoProfile;
import com.ptsd.authservice.SocialLogin.Kakao.KakaoUser;
import com.ptsd.authservice.domain.Role;
import com.ptsd.authservice.dto.UserDto;
import com.ptsd.authservice.repository.UserRepository;
import com.ptsd.authservice.SocialLogin.Google.GoogleOauth;
import com.ptsd.authservice.SocialLogin.Google.GoogleOauthToken;
import com.ptsd.authservice.SocialLogin.Google.GoogleUser;
import com.ptsd.authservice.SocialLogin.LoginTokenManager;
import com.ptsd.authservice.SocialLogin.SocialLoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.dto.UserResponse;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService{
    @Autowired
    private final GoogleOauth googleOauth;
    @Autowired
    private final KakaoOauth kakaoOauth;
    //    @Autowired
//    private final NaverOauth naverOauth;
    @Autowired
    private final LoginTokenManager loginTokenManager;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserServiceImpl userServiceImpl;
//    @Autowired
//    private final UserResponseRedisRepository userResponseRedisRepository;

    public String request(SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        switch (socialLoginType){
            case GOOGLE : redirectURL = googleOauth.getOauthRedirectURL();   break;
            case KAKAO  : redirectURL = kakaoOauth.getOauthRedirectURL();    break;
//            case NAVER  : redirectURL = naverOauth.getOauthRedirectURL();    break;
            default:{
                throw new IllegalArgumentException("Unknown Social Login Type");
            }
        }

        return redirectURL;
    }

    public UserResponse oauthLogin(SocialLoginType socialLoginType, String code, String state) {
        UserResponse userResponse;

        try {
            switch (socialLoginType){
                case GOOGLE:{
                    ResponseEntity<String> accessTokenResponse= googleOauth.requestAccessToken(code);
                    GoogleOauthToken oauthToken = googleOauth.getAccessToken(accessTokenResponse);
                    ResponseEntity<String> userInfoResponse=googleOauth.requestUserInfo(oauthToken);
                    GoogleUser googleUser= googleOauth.getUserInfo(userInfoResponse);


                    if (!userRepository.existsBySocialIdAndPlatform(googleUser.id, "GOOGLE")) {
                        UserDto userDto = UserDto.builder()
                                .socialId(googleUser.id)
                                .email(googleUser.email)
                                .nickname(googleUser.name)
                                .platform("GOOGLE")
                                .profileImage(googleUser.picture)
                                .subscribe_state(Role.FREE)
                                .build();
                        userServiceImpl.join(userDto);
                    }

                    Long userId = userServiceImpl.searchUserId(googleUser.id, String.valueOf(socialLoginType));
                    Optional<User> user = userRepository.findById(userId);

                    log.info("User Social ID -> {}", user.get().getSocialId());
                    log.info("User Nickname -> {} ", user.get().getNickname());
                    log.info("User Platform -> {}", user.get().getPlatform());

                    userResponse = UserResponse.builder()
                            .userId(userId)
                            .status("Login Success")
                            .socialId(googleUser.id)
                            .token(loginTokenManager.genToken(User.builder()
                                    .userId(userId)
                                    .social_id(googleUser.id)
                                    .email(googleUser.email)
                                    .nickname(user.get().getNickname())
                                    .platform("GOOGLE")
                                    .profileImage(user.get().getProfileImage())
                                    .subscribe_state(user.get().getSubscribe_state())
                                    .instance_id(user.get().getInstance_id())
                                    .build()))
                            .email(googleUser.email)
                            .nickname(user.get().getNickname())
                            .profile_image(user.get().getProfileImage())
                            .subscribeState(user.get().getSubscribe_state())
                            .platform(user.get().getPlatform())
                            .instanceId(user.get().getInstance_id())
                            .build();
                    log.info("TOKEN -> {}", userResponse.getToken());
                }
                break;
                case KAKAO:{
                    ResponseEntity<String> accessTokenResponse= kakaoOauth.requestAccessToken(code);
                    KakaoOauthToken oauthToken = kakaoOauth.getAccessToken(accessTokenResponse);
                    ResponseEntity<String> userInfoResponse=kakaoOauth.requestUserInfo(oauthToken);
                    ResponseEntity<String> userProfileResponse=kakaoOauth.requestUserProfile(oauthToken);
                    KakaoProfile kakaoProfile = kakaoOauth.getUserProfile(userProfileResponse);
                    KakaoUser kakaoUser = kakaoOauth.getUserInfo(userInfoResponse);

                    if (!userRepository.existsBySocialIdAndPlatform(String.valueOf(kakaoUser.id), "KAKAO")) {
                        UserDto userDto = UserDto.builder()
                                .socialId(String.valueOf(kakaoUser.id))
                                .email(kakaoUser.kakao_account.email)
                                .nickname(kakaoUser.properties.nickname)
                                .platform("KAKAO")
                                .profileImage(kakaoProfile.profileImageURL)
                                .subscribe_state(Role.FREE)
                                .build();
                        userServiceImpl.join(userDto);
                    }

                    Long userId = userServiceImpl.searchUserId(String.valueOf(kakaoUser.id), "KAKAO");
                    Optional<User> user = userRepository.findById(userId);

                    log.info("User Social ID -> {}", user.get().getSocialId());
                    log.info("User Nickname -> {} ", user.get().getNickname());
                    log.info("User Platform -> {}", user.get().getPlatform());

                    userResponse = UserResponse.builder()
                            .userId(userId)
                            .status("Login Success")
                            .socialId(String.valueOf(kakaoUser.id))
                            .token(loginTokenManager.genToken(User.builder()
                                    .userId(userId)
                                    .social_id(String.valueOf(kakaoUser.id))
                                    .email(kakaoUser.kakao_account.email)
                                    .nickname(user.get().getNickname())
                                    .platform("KAKAO")
                                    .profileImage(user.get().getProfileImage())
                                    .subscribe_state(user.get().getSubscribe_state())
                                    .instance_id(user.get().getInstance_id())
                                    .build()))
                            .email(kakaoUser.kakao_account.email)
                            .nickname(user.get().getNickname())
                            .platform("KAKAO")
                            .profile_image(user.get().getProfileImage())
                            .instanceId(user.get().getInstance_id())
                            .subscribeState(user.get().getSubscribe_state())
                            .build();

                    log.info("TOKEN -> {}", userResponse.getToken());
                }
                break;
//            case NAVER:{
//                ResponseEntity<String> accessTokenResponse= naverOauth.requestAccessToken(code,state);
//                NaverOauthToken oauthToken = naverOauth.getAccessToken(accessTokenResponse);
//                ResponseEntity<String> userInfoResponse=naverOauth.requestUserInfo(oauthToken);
//                NaverUser naverUser = naverOauth.getUserInfo(userInfoResponse);
//                userResponse = UserResponse.builder()
//                        .status("Login Success")
//                        .token(loginTokenManager.genToken(User.builder()
//                                                            .userID(naverUser.response.id)
//                                                            .email(naverUser.response.email)
//                                                            .platform("NAVER")
//                                                            .build()))
//                        .email(naverUser.response.email)
//                        .platform("NAVER")
//                        .build();
//            }
//            break;
                default:{
                    userResponse = UserResponse.builder()
                            .status("Login Failed :Unknown Social Login Type")
                            .build();
                }
            }
        } catch (Exception e) {
            userResponse = UserResponse.builder()
                    .status("Login Failed")
                    .build();
        }
        return  userResponse;
    }
    public UserResponse oauthVerifyToken(String token) throws IOException {
        UserResponse userResponse;
        if(token == null){
            userResponse = UserResponse.builder()
                    .status("Verify Fail :Empty Token")
                    .build();
        } else if(loginTokenManager.isValid(token)){
            Claims claims = loginTokenManager.getClaims(token);

            userResponse = UserResponse.builder()
                    .userId(claims.get("userId", Long.class))
                    .socialId(claims.get("socialId", String.class))
                    .status("Verify Success :Token Refreshed")
                    .token(loginTokenManager.refreshToken(token))
                    .email(claims.get("email",String.class))
                    .nickname(claims.get("nickname",String.class))
                    .platform(claims.get("platform",String.class))
                    .profile_image(claims.get("profileImage", String.class))
                    .subscribeState(Role.valueOf(claims.get("subscribe_state", String.class)))
                    .build();
        } else {
            userResponse = UserResponse.builder()
                    .status("Verify Fail :Invaild Token")
                    .build();
        }
        return userResponse;
    }
}