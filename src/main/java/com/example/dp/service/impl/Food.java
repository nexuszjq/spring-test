package com.example.dp.service.impl;

import com.example.dp.service.AbstractStrategy;
import com.example.dp.service.Strategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * desc
 *
 * @author nexus 2022/03/12 10:32
 */
@Service
public class Food extends AbstractStrategy implements Strategy {
//    private static final Food instance = new Food();
//    private Food() {
//        register();
//    }
//    public static Food getInstance() {
//        return instance;
//    }

    @PostConstruct
    public void init(){
        register();
    }

    @Override
    public void issue(Object... params) {
        System.out.println("======>Food");
    }
}
