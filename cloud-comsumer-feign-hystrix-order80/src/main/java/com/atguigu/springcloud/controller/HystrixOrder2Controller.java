package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/23
 */
@RestController
// 指明指向的全局兜底方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixOrder2Controller {

    @Resource
    private HttpServletRequest request;

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping("/order/feign/ok2")
    public String ok(String id){
        return feignPaymentService.ok(id);
    }

    @HystrixCommand
    @GetMapping("/order/feign/timeOut2")
    public String timeOut(String id){
        int i = 10/0;
        long current = System.currentTimeMillis();
        String timeOut = feignPaymentService.timeOut(id);
        System.out.println((System.currentTimeMillis() - current) / 1000);
        return timeOut;
    }

    /**
     * 兜底方法（不能带形参）
     *
     * @return
     */
    public String defaultFallback() {
        System.out.println("全局fallback来接收");
        return "全局fallback来接收";
    }

}
