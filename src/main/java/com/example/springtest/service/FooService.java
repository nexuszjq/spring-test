package com.example.springtest.service;

public interface FooService {
    void insertThenRollback();
    void invokeInsertThenRollback();
    void insertNew();
    void invokeNewInsertThenRollback();
    void invokeNewRollback();
    void insertNested();
    void invokeNestedRollback();

    void isActualTransactionActiveTest();
}
