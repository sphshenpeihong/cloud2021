package com.atguigu.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon规定不能放在@ComponentScan能扫到的地方，而是定义其它目录，等@RibbonClient注解自己指定
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/19
 */
@Configuration
public class MyRule {

    /**
     * 更改负载RestTemplate的负载均衡的策略（由轮询更改成随机）
     * @return
     */
    @Bean
    public IRule rule() {
        return new RandomRule();
    }

}
