package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/29
 */
@FeignClient(name = "cloud-payment-service", path = "/cloud-payment/repayment")
public interface RePaymentClient {

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment);

    @RequestMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id);

    @GetMapping("/discovery")
    public Object discovery() throws InterruptedException;

}
