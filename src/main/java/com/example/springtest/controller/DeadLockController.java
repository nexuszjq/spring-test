package com.example.springtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/02/09 14:30
 */
@RestController
@RequestMapping("/dead-lock")
public class DeadLockController {
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    @GetMapping("/loop")
    public String loop() {
        while (true){

        }
    }

    @GetMapping("/lock")
    public String lock() {
        new Thread(()->{
            synchronized (obj1){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("obj1 get");
                synchronized (obj2) {
                    System.out.println("obj2 get");
                }
            }

        }, "t1").start();

        new Thread(()->{
            synchronized (obj2){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("obj2 get");
                synchronized (obj1) {
                    System.out.println("obj1 get");
                }
            }

        }, "t2").start();

        return "success";
    }
}
