package com.gok.food_map.log.service;

import com.gok.food_map.user.entity.MUser;
import com.gok.food_map.user.mapper.MUserMapper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class TokenStore {
    @Resource
    private RedisTemplate<String, Long> redisTemplate;
    @Resource
    private MUserMapper userMapper; // 用于通过用户ID查询用户名

    // Token过期时间先设置成24小时
    private static final long EXPIRATION = 24 * 60 * 60;

    // 存储Token与用户ID，并刷新过期时间
    public void saveToken(String token, Long userId) {
        redisTemplate.opsForValue().set(token, userId, EXPIRATION, TimeUnit.SECONDS);
    }

    // 根据Token获取用户ID
    public Long getUserId(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    // 根据Token获取用户名（从数据库查询）
    public String getUsername(String token) {
        Long userId = getUserId(token);
        if (userId == null) {
            return null;
        }
        MUser user = userMapper.selectById(userId);
        return user != null ? user.getName() : null;
    }

    // 验证Token是否有效（存在且未过期）
    public boolean isValid(String token) {
        return redisTemplate.hasKey(token);
    }

    // 删除Token（登出时使用）
    public void removeToken(String token) {
        redisTemplate.delete(token);
    }
}
