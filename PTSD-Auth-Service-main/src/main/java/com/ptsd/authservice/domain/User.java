package com.ptsd.authservice.domain;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
@Entity
public class User {
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "social_id", nullable = false)
    private String socialId;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "nickname", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    private String nickname;
    @Column(name = "platform", nullable = false)
    private String platform;
    @Column(name = "profile_image", nullable = true)
    private String profileImage;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = true)
    @ColumnDefault("0")
    private Role subscribe_state;

    @Column(name = "instance_id", nullable = true)
    private String instance_id;

    @Builder
    public User(Long userId, String social_id, String email, String nickname, String instance_id, String platform, String profileImage, Role subscribe_state) {
        this.userId = userId;
        this.socialId = social_id;
        this.email = email;
        this.nickname = nickname;
        this.platform = platform;
        this.profileImage = profileImage;
        this.instance_id = instance_id;
        this.subscribe_state = subscribe_state;
    }

    public void updateInstanceId(String instanceId) { this.instance_id = instanceId;}

    public void update(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void updateSubScribeState(Role subscribe_state) {
        this.subscribe_state = subscribe_state;
    }

    public Map<String,Object> getHashMap(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("socialId",socialId);
        claims.put("email",email);
        claims.put("nickname", nickname);
        claims.put("platform", platform);
        claims.put("profileImage", profileImage);
        claims.put("instanceId", instance_id);
        claims.put("subscribe_state", subscribe_state);
        return claims;
    }
}