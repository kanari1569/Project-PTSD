package com.ptsd.authservice.repository;

import com.ptsd.authservice.domain.User;
import com.ptsd.authservice.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsBySocialIdAndPlatform(String socialId, String platform);
    Optional<User> findBySocialIdAndPlatform(String socialId, String platform);
    Optional<UserDto> findBySocialId(String socialId);
    Optional<User> findByUserId(Long userId);
}