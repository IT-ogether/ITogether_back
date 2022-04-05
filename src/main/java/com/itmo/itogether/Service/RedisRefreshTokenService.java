package com.itmo.itogether.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisRefreshTokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisRefreshTokenService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getRedisRefreshTokenValue(String refreshToken) {

        ValueOperations<String, String> refreshTokenValueOperations = redisTemplate.opsForValue();

        return refreshTokenValueOperations.get(refreshToken);
    }

    public void setRedisRefreshTokenValue(String refreshToken, String nickname) {

        ValueOperations<String, String> refreshTokenValueOperations = redisTemplate.opsForValue();
        refreshTokenValueOperations.set(refreshToken, nickname, Duration.ofDays(14));
    }


    public boolean isExistRefreshToken(String refreshToken) {
        return getRedisRefreshTokenValue(refreshToken) != null;
    }

    public boolean deleteRefreshToken(String refreshToken) {
        return redisTemplate.delete(refreshToken);
    }
}
