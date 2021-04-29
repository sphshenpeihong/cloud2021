package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/19
 */
@SpringBootApplication
@EnableFeignClients
public class OpenFeignMain80 {
    public static void main(String[] agrs){
        ApplicationContext ac = SpringApplication.run(OpenFeignMain80.class, agrs);
    }
}
