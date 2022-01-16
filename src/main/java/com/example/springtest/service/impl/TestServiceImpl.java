package com.example.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/26 9:50
 */
@Component
public class TestServiceImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TestServiceImpl self;

    @Transactional
    public void insertTestInnerInvoke() {
        //正常public修饰符的事务方法
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('111')");
        if (true) {
            throw new RuntimeException("need intercept");
        }
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('222')");
    }


    public void testInnerInvoke(){
        //类内部调用@Transactional标注的方法。
        self.insertTestInnerInvoke();
    }

    @Transactional
    public void insertTestCatchException() {
        try {
            jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('111')");
            if (true) {
                //运行期间抛异常
                throw new RuntimeException("need intercept");
            }
            jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('222')");
        }catch (Exception e){
            System.out.println("i catch exception");
        }
    }
}
