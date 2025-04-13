package com.fastcampus.webmvctest.config;

import com.fastcampus.webmvctest.repository.User;
import com.fastcampus.webmvctest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements ApplicationRunner {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String name = "seunggu";
        String email = "seunggu@seunggu.co.kr";
        redisTemplate.opsForValue().set("users:1:name", name);
        redisTemplate.opsForValue().set("users:1:email", email);

        if(userRepository.findById(1L).isEmpty()){
            userRepository.save(User.builder().name(name).email(email).build());
        }
    }

}
