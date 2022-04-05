package com.example.springtest.controller;

import com.example.springtest.config.LockeeperProperties;
import com.example.springtest.entity.User;
import com.example.springtest.service.impl.QueryGrantTypeService;
import com.example.springtest.service.impl.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/01/21 0:29
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    @Autowired
    private LockeeperProperties lockeeperProperties;
    @Autowired
    private RedisTestService redisTestService;

    @PostMapping("/test")
    public Callable<String> test() throws InterruptedException {
        long start = System.currentTimeMillis();
        log.info("===============start==============>");
        Callable<String> asyncProcessing = () -> {
            Thread.sleep(5000);
            log.info("===============running==============>");
            return "complete";
        };
        Thread.sleep(5000);
        log.info("===============end==============>");
        log.info("==============="+(System.currentTimeMillis()-start)+"==============>");
        return asyncProcessing;
    }

    @GetMapping
    public void test2() {
        System.out.println(lockeeperProperties.getA());
        System.out.println(lockeeperProperties.getB());
        System.out.println(lockeeperProperties.getC());
    }

    @GetMapping("/redis")
    public List<User> test3() {
        return redisTestService.find();
    }
}