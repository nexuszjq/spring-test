package com.example.springtest.service.impl;

import com.example.springtest.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Slf4j
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insertThenRollback() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invokeInsertThenRollback() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        try {
            fooService.insertThenRollback();
        } catch (RuntimeException e) {
            log.error("RollbackException", e);
        }
//        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insertNew() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void invokeNewInsertThenRollback() {
        fooService.insertNew();
        try {
            fooService.insertThenRollback();
        } catch (RuntimeException e) {
            log.error("RollbackException", e);
        }
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invokeNewRollback() {
//        fooService.insertThenRollback();
        fooService.insertNew();
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void insertNested() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invokeNestedRollback() {
//        fooService.insertThenRollback();
        fooService.insertNested();
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RuntimeException();
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void isActualTransactionActiveTest() {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
    }

    public void transactionTest() {
        transactionTemplate.executeWithoutResult((transactionStatus)->{
            jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('TEST')");
            transactionStatus.setRollbackOnly();
        });
    }

}
