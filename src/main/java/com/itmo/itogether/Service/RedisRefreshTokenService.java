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

    public String getRedisRefreshTokenValue(Integer refreshTokenIndex) {

        ValueOperations<Integer, String> refreshTokenValueOperations = redisTemplate.opsForValue();

        return refreshTokenValueOperations.get(refreshTokenIndex);
    }

    public void setRedisRefreshTokenValue(Integer refreshTokenIndex, String refreshToken) {

        ValueOperations<Integer, String> refreshTokenValueOperations = redisTemplate.opsForValue();
        refreshTokenValueOperations.set(refreshTokenIndex, refreshToken, Duration.ofDays(14));
    }


//    public boolean isExistRefreshToken(String refreshToken) {
//        return getRedisRefreshTokenValue(refreshToken) != null;
//    }

    public boolean deleteRefreshToken(String refreshToken) {
        return redisTemplate.delete(refreshToken);
    }
}
