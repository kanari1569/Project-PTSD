//package com.mpt.authservice;
//
//import com.mpt.authservice.dto.UserDto;
//import com.mpt.authservice.repository.RedisRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class RedisRepositoryTest {
//
//    @Autowired
//    private RedisRepository redisRepository;
//
//    @Test
//    void test() {
//        UserDto user = new UserDto(1L, "wada", "dwadaw@g.com", "KAKAO");
//
//        redisRepository.save(user);
//
//        redisRepository.findById(user.getUser_id());
//
//        redisRepository.count();
//
//        redisRepository.delete(user);
//    }
//}
