package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
@RestController
public class HystrixOrderController {

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping("/order/feign/ok")
    public String ok(String id){
        return feignPaymentService.ok(id);
    }

    @HystrixCommand(fallbackMethod = "fallBack1", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50000")
    })
    @GetMapping("/order/feign/timeOut")
    public String timeOut(String id){
        long current = System.currentTimeMillis();
        String timeOut = feignPaymentService.timeOut(id);
        System.out.println((System.currentTimeMillis() - current) / 1000);
        return timeOut;
    }

    @GetMapping("/order/feign/timeOut3")
    public String timeOut3(String id){
        int i = 10/0;
        long current = System.currentTimeMillis();
        String timeOut = feignPaymentService.timeOut(id);
        System.out.println((System.currentTimeMillis() - current) / 1000);
        return timeOut;
    }

    public String fallBack1(String id) {
        return "我是客户端，我的要求高一点，超时啦1111111";
    }

}
