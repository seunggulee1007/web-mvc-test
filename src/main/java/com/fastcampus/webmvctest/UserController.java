package com.fastcampus.webmvctest;

import com.fastcampus.webmvctest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "message", "Hello, World!"
        );
    }

    @GetMapping("/users/1/cache")
    public Map<String, String> getUserCache() {
        String name = redisTemplate.opsForValue().get("users:1:name");
        String email = redisTemplate.opsForValue().get("users:1:email");
        return Map.of(
                "name", name == null ? "" : name,
                "email", email == null ? "" : email
        );
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(new User());
    }

}
