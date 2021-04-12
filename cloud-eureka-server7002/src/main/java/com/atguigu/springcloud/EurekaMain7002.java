package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Shen Peihong on 2021/4/12
 * Description:
 *
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {

    public static void main(String[] agrs){
        SpringApplication.run(EurekaMain7002.class, agrs);
    }

}
