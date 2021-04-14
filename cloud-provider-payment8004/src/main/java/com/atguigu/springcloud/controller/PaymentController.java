package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description:
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private Integer serverPort;

    @RequestMapping("zk")
    public String zk() {
        return "Hello,Wolrd! serverPort ：" + serverPort;
    }
}
