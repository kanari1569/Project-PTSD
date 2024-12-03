package com.ptsd.authservice.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ptsd.authservice.SocialLogin.LoginTokenManager;
import com.ptsd.authservice.SocialLogin.SocialLoginType;
import com.ptsd.authservice.domain.Role;
import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import javax.servlet.ServletRequest;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ptsd.authservice.dto.UserResponse;
import com.ptsd.authservice.service.OauthService;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OauthController {
    @Autowired
    OauthService oauthService;

    @Autowired
    UserRepository userRepository;

    private final LoginTokenManager loginTokenManager;

    @GetMapping("/login/{socialLoginType}")
    public void socialLogin(@PathVariable(name="socialLoginType") String SocialLoginPath,@RequestParam(name = "redirect_url", required = false) String redirect_url, HttpServletRequest  request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.setAttribute("redirect_url",redirect_url);

        SocialLoginType socialLoginType = SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        response.sendRedirect(oauthService.request(socialLoginType));
        // return ResponseEntity.ok().body(oauthService.request(socialLoginType));
    }

    @GetMapping(value = "/login/{socialLoginType}/redirection")
    public void socialLoginRedirect(HttpSession session, @PathVariable(name="socialLoginType") String SocialLoginPath, @RequestParam(name = "code") String code, @RequestParam(name = "state", required = false) String state,HttpServletRequest request, HttpServletResponse response) throws IOException{
        SocialLoginType socialLoginType = SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        UserResponse userResponse = oauthService.oauthLogin(socialLoginType,code,state);
        session = request.getSession();
        String redirect_url = (String)session.getAttribute("redirect_url");

        if(redirect_url != null){
            session.removeAttribute("redirect_url");
            response.sendRedirect(redirect_url+"?userResponse="+URLEncoder.encode(userResponse.toJson()));
        }
    }

	// 파라미터로 전달받은 토큰 처리
//    @GetMapping("/token")
//    public ResponseEntity<UserResponse> validCheck(@RequestParam(name = "ACCESS_TOKEN", required = false) String token) throws IOException {
//        UserResponse userResponse = oauthService.oauthVerifyToken(token);
//        return ResponseEntity.ok().body(userResponse);
//    }

    // 헤더로 전달받은 토큰 처리
    @GetMapping(value = "/access_token_info")
    public ResponseEntity<UserResponse> getAccessTokenInfo(@RequestHeader HttpHeaders httpHeaders) throws IOException {
        String token = httpHeaders.getFirst("Authorization");

        if (token == null) {
            UserResponse userResponse = UserResponse.builder()
                    .status("Empty Token")
                    .build();
            return ResponseEntity.status(403).body(userResponse);
        }


        if(token.toLowerCase().startsWith("Bearer".toLowerCase())) {
            token = token.substring("Bearer".length()).trim();
        }

        Claims claims = loginTokenManager.getClaims(token);

        UserResponse userResponse = userResponse = UserResponse.builder()
                .userId(claims.get("userId", Long.class))
                .socialId(claims.get("socialId", String.class))
                .email(claims.get("email",String.class))
                .nickname(claims.get("nickname",String.class))
                .platform(claims.get("platform",String.class))
                .profile_image(claims.get("profileImage", String.class))
                .subscribeState(Role.valueOf(claims.get("subscribe_state", String.class)))
                .build();

        Optional<User> user = userRepository.findBySocialIdAndPlatform(userResponse.getSocialId(), userResponse.getPlatform());

            try {

                if (checkUser(userResponse, user)) {
                    userResponse = UserResponse.builder()
                            .userId(user.get().getUserId())
                            .nickname(user.get().getNickname())
                            .subscribeState(user.get().getSubscribe_state())
                            .status("Success")
                            .build();
                    return ResponseEntity.ok().body(userResponse);
                }

                userResponse = UserResponse.builder()
                        .status("Verify Fail :Invaild Token")
                        .build();

            } catch (NoSuchElementException e) {
                userResponse = UserResponse.builder()
                        .status("Verify Fail :Invaild Token")
                        .build();
            }

            return ResponseEntity.status(403).body(userResponse);
    }

    public boolean checkUser(UserResponse userResponse, Optional<User> user) {

        String nickname = userResponse.getNickname();
        String profileImage = userResponse.getProfile_image();
        String instanceId = userResponse.getInstanceId();

        if (instanceId != null && user.get().getNickname().equals(nickname) && user.get().getProfileImage().equals(profileImage) && user.get().getInstance_id().equals(instanceId)) {
            return true;
        } else if (instanceId == null && user.get().getNickname().equals(nickname) && user.get().getProfileImage().equals(profileImage)){
            return true;
        } else {
            return false;
        }

    }

}
