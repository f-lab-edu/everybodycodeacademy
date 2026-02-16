package com.everycodeacademy.backend.security;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

@Service
public class TokenStoreService {

    private static final String REFRESH_KEY_PREFIX = "auth:refresh:user:";
    private static final String BLACKLIST_KEY_PREFIX = "auth:blacklist:";

    private final StringRedisTemplate redisTemplate;

    public TokenStoreService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(long userId, String refreshToken, long ttlMillis) {
        redisTemplate.opsForValue().set(REFRESH_KEY_PREFIX + userId, refreshToken, Duration.ofMillis(ttlMillis));
    }

    public String getRefreshToken(long userId) {
        return redisTemplate.opsForValue().get(REFRESH_KEY_PREFIX + userId);
    }

    public void deleteRefreshToken(long userId) {
        redisTemplate.delete(REFRESH_KEY_PREFIX + userId);
    }

    public void blacklistAccessToken(String accessToken, long ttlMillis) {
        if (ttlMillis <= 0) return;
        redisTemplate.opsForValue().set(BLACKLIST_KEY_PREFIX + encode(accessToken), "1", Duration.ofMillis(ttlMillis));
    }

    public boolean isBlacklisted(String accessToken) {
        Boolean exists = redisTemplate.hasKey(BLACKLIST_KEY_PREFIX + encode(accessToken));
        return Boolean.TRUE.equals(exists);
    }

    private String encode(String token) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }
}
