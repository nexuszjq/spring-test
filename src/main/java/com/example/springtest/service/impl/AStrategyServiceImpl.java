package com.example.springtest.service.impl;

import com.example.springtest.service.StrategyService;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/18 16:09
 */
@Component("AAA")
public class AStrategyServiceImpl implements StrategyService {

    @Override
    public String invoke() {
        return "A";
    }
}
