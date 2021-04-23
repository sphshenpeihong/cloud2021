package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
@SpringBootApplication
// 服务客户端
@EnableEurekaClient
// 开启openFeign功能
@EnableFeignClients
// 客户端侧开启hystrix
@EnableHystrix
public class ComsumerHystrixOrder80 {

    public static void main(String[] agrs){
        SpringApplication.run(ComsumerHystrixOrder80.class, agrs);
    }

}
