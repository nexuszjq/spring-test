package com.example.springtest;

import com.example.springtest.service.ResourceLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 19:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceLockTests {

    @Autowired
//    @Qualifier("mysqlLockService")
//    @Qualifier("zooKeeperLockService")
    @Qualifier("redisLockService")
    ResourceLockService resourceLockService;

    @Test
    public void test() throws InterruptedException {
        LockRunnable lockRunnable = new LockRunnable();
        Thread t1 = new Thread(lockRunnable, "ThreadA");
        Thread t2 = new Thread(lockRunnable, "ThreadB");
        Thread t3 = new Thread(lockRunnable, "ThreadC");
        t1.start();
        t2.start();
        t3.start();
        Thread.currentThread().join();

    }

    public class LockRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    resourceLockService.testLock("resourceXXX");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
