package com.example.springtest.service.impl;

import com.example.springtest.service.ResourceLockService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 18:32
 */
@Service
public class ZooKeeperLockService implements ResourceLockService {
//    @Autowired
//    private CuratorFramework curatorClient;

    @Override
    public void testLock(String resourceName) throws Exception {
//        InterProcessMutex lock = new InterProcessMutex(curatorClient, "/locks/" + resourceName);
//
//        lock.acquire();
//        System.out.println(Thread.currentThread().getName() + " acqured lock for resourceXXX");
//        Thread.sleep(5000);
//        if (lock.isAcquiredInThisProcess()) {
//            lock.release();
//        }
//        System.out.println(Thread.currentThread().getName() + " released lock for resourceXXX");
//        Thread.sleep(5000);
    }
}
