package com.atguigu.springcloud;

import com.atguigu.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description:
 *
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
// 指定当调用哪个微服务时，需要更换成自定义负载均衡策略
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE", configuration = MyRule.class)
public class OrderMain80 {

    public static void main(String[] agrs){
        SpringApplication.run(OrderMain80.class, agrs);
    }

}
