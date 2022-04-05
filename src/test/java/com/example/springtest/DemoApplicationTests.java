package com.example.springtest;

import com.example.springtest.service.FutureService;
import com.example.springtest.service.TransService;
import com.example.springtest.service.impl.MyApplicationContext;
import com.example.springtest.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/26 9:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    TestServiceImpl testService;
    @Resource
    FutureService futureService;
    @Resource
    TransService transService;
    @Resource
    MyApplicationContext myApplicationContext;

    /**
     * 测试内部调用@Transactional标注方法
     */
    @Test
    public void  testInnerInvoke(){
        //测试外部调用事务方法是否正常
        //testService.insertTestInnerInvoke();
        //测试内部调用事务方法是否正常
        testService.testInnerInvoke();
    }

    @Test
    public void testCatchException(){
        testService.insertTestCatchException();
    }

    @Test
    public void testRest() {
        futureService.futureTest2();
    }

    @Test
    public void test() {
        transService.F6();
    }

    @Test
    public void testDemo() {
        myApplicationContext.getInvoke();
    }

    @Test
    public void testRollback() {
        futureService.futureRollbackTest();
    }
}
