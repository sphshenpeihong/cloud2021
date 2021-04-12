package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description: 消费模块远程调用提供者模块
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/order")
public class OrderCtl {

    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/create")
    public CommonResult create(HttpServletRequest request, Payment payment){
        // 使用的postForObject方法，发起请求时，使用的请求头类型是application/json的
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult create(@PathVariable(name = "id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

}
