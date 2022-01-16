package com.example.springtest.service.impl;

import com.example.springtest.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/11 15:11
 */
@Service
public class TransServiceImpl implements TransService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransService self;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void A() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        self.B();
        //情况1
//        throw new RuntimeException();
    }

    @Override
    public void B() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        self.C();
        //情况2
//        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void C() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('CCC')");
        //情况3
        throw new RuntimeException();
    }

    //------------------------------------------------------------------------------------------------------------------------

    // Transaction rolled back because it has been marked as rollback-only
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void D() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('111')");
        try {
            self.E();
        } catch (Exception e) {

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void E() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('222')");
        throw new RuntimeException();
    }

    //------------------------------------------------------------------------------------------------------------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void F1() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('111')");
        self.F2();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void F2() {
        try {
//            self.F3();
//            self.F4();
            self.F5();
        } catch (Exception ex) {

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void F3() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('333')");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void F4() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('444')");
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void F5() {
        F3();
        F4();
    }
//---------------------------------------------------------------------------------------------------------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void F6() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('F6')");
        F7();
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void F7() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('F7')");
    }
//--------------------------------------------------------------------------------------------------------------------------



}
