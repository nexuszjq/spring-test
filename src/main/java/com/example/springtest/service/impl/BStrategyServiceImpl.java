package com.example.springtest.service.impl;

import com.example.springtest.service.StrategyService;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/18 16:10
 */
@Component("BBB")
public class BStrategyServiceImpl implements StrategyService {

    @Override
    public String invoke() {
        return "B";
    }
}
