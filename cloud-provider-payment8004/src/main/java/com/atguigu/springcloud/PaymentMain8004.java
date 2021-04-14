package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description:
 *
 * @since 1.0.0
 */
@SpringCloudApplication
//1、用到DiscoveryClient服务发现的功能时，微服务也需要加上这个，才能拿到服务信息
//2、zookeeper和consul做注册中心的话，客户端主启动类得加上这个才能被注册
@EnableDiscoveryClient
public class PaymentMain8004 {

    public static void main(String[] agrs){
        SpringApplication.run(PaymentMain8004.class, agrs);
    }

}
