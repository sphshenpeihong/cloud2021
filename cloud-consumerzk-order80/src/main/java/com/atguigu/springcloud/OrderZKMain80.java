package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description:
 *
 * @since 1.0.0
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan({"com.atguigu", "com.atguigu.springcloud.config"})
public class OrderZKMain80 {

    public static void main(String[] agrs){
        SpringApplication.run(OrderZKMain80.class, agrs);
    }

}
