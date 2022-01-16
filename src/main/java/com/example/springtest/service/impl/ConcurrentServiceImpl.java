package com.example.springtest.service.impl;

import com.example.springtest.service.ConcurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/10 9:31
 */
@Service
public class ConcurrentServiceImpl implements ConcurrentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void CompletableFutureTest() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");

        CompletableFuture<Object> cf1 = CompletableFuture.supplyAsync(() ->{
            jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
            throw new RuntimeException("异步报错");
        }).exceptionally(e->{
            throw new RuntimeException(e.getMessage());
        });

        System.out.println(cf1.join());
    }
}
