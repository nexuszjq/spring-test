package com.example.springtest.service.impl;

import com.example.springtest.service.FutureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/27 20:31
 */
@Slf4j
@Service
public class FutureServiceImpl implements FutureService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FutureService self;
    @Autowired
    @Qualifier("CommonAsyncTaskExecutor")
    private ThreadPoolExecutor executor;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String invokeInterface() {
        String str = restTemplate.getForObject("http://hq.sinajs.cn/list=s_sz300170", String.class);
//        if (Math.random() > 0.5) {
//            throw new RuntimeException("random error");
//        }
        return str;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void futureTest() {
        long start = System.currentTimeMillis();
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        AtomicInteger failTimes = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            futureList.add(CompletableFuture.supplyAsync(() -> {
                return self.invokeInterface();
            }, executor).exceptionally(ex->{
                failTimes.incrementAndGet();
                log.info("error：" + ex.getMessage());
                return ex.getMessage();
            }).thenAccept(syncList::add));
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        log.info("接口失败次数：" + failTimes);
        syncList.stream().forEach(System.out::println);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void futureTest2() {
        long start = System.currentTimeMillis();
        List<String> syncList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            syncList.add(self.invokeInterface());
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Integer index) {
        jdbcTemplate.update("INSERT INTO FOO (ID,BAR) VALUES (?,?)", new Object[]{index, "bar"+index});
        if (Math.random() > 0.5) {
            throw new RuntimeException("random error");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void futureRollbackTest() {
        AtomicInteger fail = new AtomicInteger(0);
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        IntStream.range(0,100).parallel().forEach(i->{
            futureList.add(CompletableFuture.runAsync(() -> {
               self.insert(i);
            }, executor).exceptionally(ex->{
                log.info("error：" + ex.getMessage());
                fail.incrementAndGet();
                return null;
            }));
        });
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        System.out.println("fail count：" + fail);
    }
}
