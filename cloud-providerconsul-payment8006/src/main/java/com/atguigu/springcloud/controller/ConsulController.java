package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sphong
 * @version: 1.0
 * @date 2021/4/15
 * @since V1.0.0.2
 */
@RestController
@RequestMapping("/consul")
public class ConsulController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment")
    public String payment() {
        return "payment，serverPort：" + serverPort;
    }

}
