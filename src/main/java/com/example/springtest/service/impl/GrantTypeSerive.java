package com.example.springtest.service.impl;

import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2022/01/21 0:20
 */
@Service
public class GrantTypeSerive {

    public String funcA(String resourceId){
        System.out.println("===============>A execute");
        return "A" + resourceId;
    }
    public String funcB(String resourceId){
        System.out.println("===============>B execute");
        return "B" + resourceId;
    }
    public String funcC(String resourceId){
        System.out.println("===============>C execute");
        return "C" + resourceId;
    }
}
