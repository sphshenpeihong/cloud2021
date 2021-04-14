package com.atguigu.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description:
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrderZKController {

    // 调用payment的地址
    private final static String PAYMENT_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 这里使用RestTemplate远程调用8004的接口
     *
     * @return
     */
    @RequestMapping("/zk")
    public String orderZK() {


        ResponseEntity<String> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/zk", null, String.class);
        return entity.getBody();

    }

}
