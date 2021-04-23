package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/21
 */
@SpringBootApplication
@EnableEurekaClient
// 激活Hystrix功能
@EnableHystrix
public class PaymentHystrixMain8008 {

    public static void main(String[] agrs){
        SpringApplication.run(PaymentHystrixMain8008.class, agrs);
    }

}
