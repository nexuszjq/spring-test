package com.example.springtest;

import com.example.dp.context.StrategyContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * desc
 *
 * @author nexus 2022/03/12 10:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {

    @Test
    public void test() {
        StrategyContext.getStrategy("Waimai").issue();
        StrategyContext.getStrategy("Hotel").issue();
        StrategyContext.getStrategy("Food").issue();
    }
}
