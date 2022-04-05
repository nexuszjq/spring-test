package com.example.springtest.controller;

import com.example.springtest.annotation.RedisRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RedisLimitController {

    @RedisRateLimiter
    @GetMapping("/test1")
    public String test1() {
        log.info("【test1】被执行了。。。。。");
        return "成功访问到api [1]~";
    }

    @RedisRateLimiter(max = 1,limitType = RedisRateLimiter.LimitType.IP,timeLimitLength = 20,timeLimitLengthUnit = TimeUnit.SECONDS)
    @GetMapping("/test2")
    public String test2() {
        log.info("【test2】被执行了。。。。。");
        return "成功访问到api [2]~";
    }

    @RedisRateLimiter(max = 5,limitType = RedisRateLimiter.LimitType.IP,timeLimitLength = 1,timeLimitLengthUnit = TimeUnit.MINUTES)
    @GetMapping("/test3")
    public String test3() {
        log.info("【test3】被执行了。。。。。");
        return "成功访问到api [3]~";
    }
}