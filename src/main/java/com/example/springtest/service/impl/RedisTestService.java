package com.example.springtest.service.impl;

import com.example.springtest.dao.UserMapper;
import com.example.springtest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * desc
 *
 * @author nexus 2022/02/15 15:07
 */
@Service
public class RedisTestService {

    private final RedisTemplate redisTemplate;
    private final UserMapper userMapper;

    @Autowired
    public RedisTestService(RedisTemplate redisTemplate,
                            UserMapper userMapper) {
        this.redisTemplate = redisTemplate;
        this.userMapper = userMapper;
    }

//    @SuppressWarnings("unchecked")
    public List<User> find() {
        String key = "product:product-category";
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            synchronized (this) {
                obj = redisTemplate.opsForValue().get(key);
                if (obj != null) {
                    return (List<User>) obj;
                }
                List<User> users = userMapper.selectUser();
                Duration expire = Duration.ofHours(2L).plus(Duration.ofSeconds((int)(Math.random() * 1000)));
                redisTemplate.opsForValue().set(key, users, expire);
                return users;
            }
        } else {
            return (List<User>) obj;
        }
    }
}
