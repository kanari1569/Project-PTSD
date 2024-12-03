package com.ptsd.authservice.SocialLogin;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ptsd.authservice.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginTokenManager {
    @Value("${Token.secret-key}")
    private String SECRET_KEY;

    public String genToken(User user){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .setSubject("Login")
                .setClaims(user.getHashMap())
                .setIssuer("Auth-token")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(9999, ChronoUnit.HOURS)))
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return genToken(User.builder()
                .userId(claims.get("userId", Long.class))
                .social_id(claims.get("socialId",String.class))
                .email(claims.get("email",String.class))
                .nickname(claims.get("nickname", String.class))
                .profileImage(claims.get("profileImage", String.class))
                .platform(claims.get("platform",String.class))
                .build());
    }

//    public String refreshToken(Claims claims) {
//        return genToken(User.builder()
//                .social_id(claims.get("social_id",String.class))
//                .email(claims.get("email",String.class))
//                .nickname(claims.get("nickname", String.class))
//                .platform(claims.get("platform",String.class))
//                .build());
//    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}