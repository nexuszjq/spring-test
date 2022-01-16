package com.example.springtest.service.impl;

import com.example.springtest.dao.ResourceLockMapper;
import com.example.springtest.entity.ResourceLock;
import com.example.springtest.service.ResourceLockService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 18:32
 */
@Service
public class MysqlLockService implements ResourceLockService {
    @Resource
    private ResourceLockMapper resourceLockMapper;

    public void lock(String resourceName) {
        while (true) {
            if (lockResource(resourceName)) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public boolean lockResource(String resourceName) {
        String threadName = Thread.currentThread().getName();
        List<ResourceLock> resourceLocks = resourceLockMapper.selectResourceLock(resourceName);
        if (CollectionUtils.isNotEmpty(resourceLocks)) {
            if (threadName.equals(resourceLocks.get(0).getNodeInfo())) {
                return true;
            } else {
                return false;
            }
        } else {
            Date date = new Date();
            ResourceLock resourceLock = new ResourceLock();
            resourceLock.setResourceName(resourceName);
            resourceLock.setNodeInfo(threadName);
            resourceLock.setCount(1L);
            resourceLock.setCreateTime(date);
            resourceLock.setUpdateTime(date);
            try {
                resourceLockMapper.insert(resourceLock);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Transactional
    public void unLock(String resourceName) {
        String threadName = Thread.currentThread().getName();
        List<ResourceLock> resourceLocks = resourceLockMapper.selectResourceLock(resourceName);
        if (CollectionUtils.isNotEmpty(resourceLocks)) {
            if (threadName.equals(resourceLocks.get(0).getNodeInfo())) {
                resourceLockMapper.delete(resourceName);
            }
        }
    }

    @Override
    public void testLock(String resourceName) throws Exception {
        lock(resourceName);
        System.out.println(Thread.currentThread().getName() + " acqured lock for resourceXXX");
        Thread.sleep(5000);
        unLock(resourceName);
        System.out.println(Thread.currentThread().getName() + " released lock for resourceXXX");
        Thread.sleep(5000);
    }
}
