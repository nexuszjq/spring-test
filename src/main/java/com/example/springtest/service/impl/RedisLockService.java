package com.example.springtest.service.impl;

import com.example.springtest.service.ResourceLockService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 18:31
 */
@Service
public class RedisLockService implements ResourceLockService {

    @Override
    public void testLock(String resourceName) throws Exception {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000000).setAddress("redis://localhost:6379");

        RedissonClient redissonClient = Redisson.create(config);
        RLock rLock = redissonClient.getLock("resourceName");
        rLock.lock();
        System.out.println(Thread.currentThread().getName() + " acqured lock for resourceXXX");
        Thread.sleep(5000);
        rLock.unlock();
        System.out.println(Thread.currentThread().getName() + " released lock for resourceXXX");
        Thread.sleep(5000);
        redissonClient.shutdown();
    }
}
