package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixCurcuitBreakerService;
import com.atguigu.springcloud.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
@RestController
public class HystrixController {

    @Resource
    private HystrixService hystrixService;

    @Resource
    private HystrixCurcuitBreakerService hystrixCurcuitBreakerService;

    @GetMapping("/hystrix/ok")
    public String ok(String id) {
        return hystrixService.ok(id);
    }

    @GetMapping("/hystrix/timeOut")
    public String timeOut(String id) {
        return hystrixService.timeOut(id);
    }

    @GetMapping("/hystrix/curcuit/breaker")
    public String breaker(Integer id){
        return hystrixCurcuitBreakerService.curcuitBreakerOK(id);
    }
}
