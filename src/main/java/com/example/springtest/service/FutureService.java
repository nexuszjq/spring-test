package com.example.springtest.service;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/11/27 20:31
 */
public interface FutureService {

    String invokeInterface();

    void futureTest();

    void futureTest2();

    void insert(Integer index);

    void futureRollbackTest();
}
