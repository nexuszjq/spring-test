package com.example.springtest;

import com.example.springtest.config.LockeeperProperties;
import com.example.springtest.service.ConcurrentService;
import com.example.springtest.service.FooService;
import com.example.springtest.service.TransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class SpringTestApplication implements ApplicationRunner {
    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ConcurrentService concurrentService;
    @Autowired
    private TransService transService;

    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        try {
//            fooService.invokeNestedRollback();
//        } catch (Exception e) {
//
//        }

//        concurrentService.CompletableFutureTest();

//        try {
//            transService.F1();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        log.info("AAA {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='AAA'", Long.class));
//        log.info("BBB {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='BBB'", Long.class));
//        log.info("CCC {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='CCC'", Long.class));
    }
}
