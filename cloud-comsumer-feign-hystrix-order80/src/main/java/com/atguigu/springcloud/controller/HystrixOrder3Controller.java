package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/25
 */
@RestController
public class HystrixOrder3Controller {

    @Resource
    private FeignPaymentService feignPaymentService;

    // 由于fallback是在feign接口上大做文章，故Controller层不需要再加任何注解了
    @GetMapping("/order/feign/ok3")
    public String ok(String id){
        return feignPaymentService.ok(id);
    }

}
