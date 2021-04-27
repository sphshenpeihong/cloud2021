package com.atguigu.springclou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/27
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {
    public static void main(String[] agrs){
        SpringApplication.run(GatewayMain9527.class, agrs);
    }
}
