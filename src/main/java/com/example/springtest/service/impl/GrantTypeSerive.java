package com.example.springtest.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/01/21 0:20
 */
@Service
public class GrantTypeSerive {

    public String getNew(){
        System.out.println("===============>A execute");
        return "NEW" + System.currentTimeMillis();
    }
    public String getCancel(){
        System.out.println("===============>B execute");
        return "CANCEL" + System.currentTimeMillis();
    }
    public String getUpdate(){
        System.out.println("===============>C execute");
        return "UPDATE" + System.currentTimeMillis();
    }
}
