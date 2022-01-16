package com.example.springtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/23 14:06
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public ValueOperations<String, String> getValueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }
    @Bean
    public HashOperations<String, String, String> getHashOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }
    @Bean
    public ListOperations<String, String> getListOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForList();
    }
    @Bean
    public SetOperations<String, String> getSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForSet();
    }
    @Bean
    public ZSetOperations<String, String> getZSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
